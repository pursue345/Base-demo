package com.donggl.common.batch;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AbstractScheduleExecutor
 * @Description 批量处理接口类
 * @Author donggl
 * @Date 2023/3/22 21:41
 * @Version 1.0
 */
public abstract class AbstractScheduleExecutor<T> {
    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 一次批量存储数据大小
     */
    private int batchSize;
    /**
     * 守护线程检查时间间隔，单位为毫秒，默认取10秒
     */
    private int batchCheckInterval;

    /**
     * 批量存储队列
     */
    private LinkedBlockingQueue<T> queue;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), r -> new Thread(r, "ScheduleExecutor"),
            new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * @param batchSize          每次批量执行大小
     * @param batchCheckInterval 间隔时间
     * @param queueSize          队列大小
     */
    public AbstractScheduleExecutor(int batchSize, int batchCheckInterval, int queueSize) {
        Preconditions.checkArgument(batchSize > 0, "batchSize必须大于0");
        Preconditions.checkArgument(batchCheckInterval > 0, "batchCheckInterval必须大于0");
        Preconditions.checkArgument(queueSize > 0, "queueSize必须大于0");
        this.batchSize = batchSize;
        this.batchCheckInterval = batchCheckInterval;
        // 默认存储队列管理10000条数据
        this.queue = new LinkedBlockingQueue<>(queueSize);
        /**
         * 守护线程任务
         */
        threadRun();
    }

    private void threadRun() {
        DaemonTask daemonTask = new DaemonTask();
        if (!daemonTask.isStarted()) {
//            new Thread(daemonTask).start();
            threadPoolExecutor.execute(daemonTask);
        }
    }

    /**
     * 执行器
     *
     * @param list 数据列表
     */
    protected abstract void execute(List<T> list);

    /**
     * 执行批量存储
     *
     * @param forceStorage = false时，队列满足一次批量存储数量时才执行，true时，不管数量多少均执行一次批量存储
     */
    private void doBatchStorage(boolean forceStorage) {
        // 若队列超过一次批量存储数量，则执行出队列，进行批量存储
        if (forceStorage || queue.size() >= batchSize) {
            List<T> storageDataList = new ArrayList<>();
            T storageData = queue.poll();
            if (storageData == null) {
                return;
            }
            // 队列为空或达到一次批量存储上限时
            do {
                storageDataList.add(storageData);
                if (storageDataList.size() > batchSize) {
                    break;
                }
                storageData = queue.poll();
            } while (storageData != null);

            try {
                execute(storageDataList);
            } catch (Exception e) {
                logger.error("批量处理异常", e);
            }
        }
    }

    /**
     * 将待存储对象入队列
     *
     * @param data data
     */
    public void put(T data) {
        if (data != null) {
            // 若队列超过一次批量存储数量，则执行出队列，进行批量存储
            if (queue.size() >= batchSize) {
                doBatchStorage(false);
            }
            try {
                queue.put(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class DaemonTask implements Runnable {
        private boolean keepAlive = true;
        private boolean started = false;

        @Override
        public void run() {
            started = true;
            while (keepAlive) {
                // 队列非空时，强制执行一次入队列操作
                if (!queue.isEmpty()) {
                    doBatchStorage(true);
                }
                // 固定频率存储
                try {
                    Thread.sleep(batchCheckInterval);
                } catch (InterruptedException e) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("中断事件唤醒批量存储守护线程", e);
                    }
                }
            }
        }

        /**
         * 是否存活
         *
         * @return
         */
        public boolean isKeepAlive() {
            return keepAlive;
        }

        /**
         * 设置存活
         *
         * @param keepAlive
         */
        public void setKeepAlive(boolean keepAlive) {
            this.keepAlive = keepAlive;
        }

        public boolean isStarted() {
            return started;
        }

    }
}
