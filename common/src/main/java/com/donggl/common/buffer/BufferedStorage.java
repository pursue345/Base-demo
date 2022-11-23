package com.donggl.common.buffer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @ClassName BufferedStorage
 * @Description 缓冲区，缓冲数据，批量处理
 * @Author donggl
 * @Date 2022/11/21 17:19
 * @Version 1.0
 */

public class BufferedStorage<T> {

    private static ScheduledThreadPoolExecutor scheduledExecutorService
            = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.CallerRunsPolicy());
    private final int buffSize;
    private List<T> buff;
    private final Consumer<List<T>> flushCallback;
    public BufferedStorage(final Consumer<List<T>> flushCallback) {
        this(flushCallback, 10);
    }

    public BufferedStorage(final Consumer<List<T>> flushCallback, final int buffSize) {
        this.buffSize = buffSize;
        this.flushCallback = flushCallback;
        this.buff = new LinkedList<>();
        scheduledExecutorService.scheduleWithFixedDelay(this::flush, 10, 10, TimeUnit.MINUTES);
    }

    public BufferedStorage(final Consumer<List<T>> flushCallback, final int buffSize,
                           final long initialDelay, final long delay, final TimeUnit unit) {
        this.buffSize = buffSize;
        this.flushCallback = flushCallback;
        this.buff = new LinkedList<>();
        scheduledExecutorService.scheduleWithFixedDelay(this::flush, initialDelay, delay, unit);

    }

    public synchronized void write(T t) {
        this.buff.add(t);
        if (this.buff.size() >= buffSize) {
            flush();
        }
    }

    public synchronized void flush() {
        if (!this.buff.isEmpty()) {
            final List<T> list = buff;
            this.buff = new LinkedList<>();
            this.flushCallback.accept(list);
        }
    }
}
