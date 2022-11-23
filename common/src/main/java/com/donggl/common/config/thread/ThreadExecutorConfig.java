package com.donggl.common.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 线程池配置类
 * @author donggl
 * @date 2022/11/10 14:38
 * @version 1.0
 */
@Configuration
public class ThreadExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ThreadExecutorConfig.class);

    @Resource
    private TaskThreadPoolConfig config;

    @Bean
    public ThreadPoolTaskExecutor globalUserScoreExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 获取可用处理器的Java虚拟机的数量（未必能准确获取到CPU核心数量）
        int core = Runtime.getRuntime().availableProcessors();
        logger.info("正在创建线程池globalUserScoreExecutor，线程池核心数量：" + core);
        // 核心线程数
        executor.setCorePoolSize(config.getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        // 除核心线程外的线程存活时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        // 等待队列数量
        executor.setQueueCapacity(config.getQueueCapacity());
        // 设置线程前缀名称
        executor.setThreadNamePrefix("globalUserScoreExecutor-");
        // 设置拒绝策略（线程不够用时由调用的线程处理该任务）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 停机策略：该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的Bean，
        // 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(60);
        executor.setTaskDecorator(new LoggerTaskDecorator());
        return executor;
    }
}
