package com.donggl.common.buffer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AbstractPublisher
 * @Description 缓冲区发布类
 * @Author donggl
 * @Date 2022/11/21 17:18
 * @Version 1.0
 */
public abstract class AbstractPublisher<T> {

    private final BufferedStorage<T> storage;

    public AbstractPublisher() {
        this(10);
    }

    public AbstractPublisher(final int buffSize) {
        this.storage = new BufferedStorage<>(this::publish, buffSize);
    }

    public AbstractPublisher(final int buffSize, final long initialDelay, final long delay, final TimeUnit unit) {
        this.storage = new BufferedStorage<>(this::publish, buffSize, initialDelay, delay, unit);
    }


    public void destroy() {
        storage.flush();
    }

    /**
     * 存数据到缓冲区
     *
     * @param t 数据对象
     */
    public void push(T t) {
        storage.write(t);
    }

    /**
     * 批量处理数据
     *
     * @param dataList 数据集合
     */
    public abstract void publish(final List<T> dataList);
}
