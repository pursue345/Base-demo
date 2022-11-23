package com.donggl.common.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @description: 线程池mdc装饰类
 * @author donggl
 * @date 2022/11/10 14:36
 * @version 1.0
 */
public class LoggerTaskDecorator implements TaskDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTaskDecorator.class);

    @Override
    @Nonnull
    public Runnable decorate(Runnable task) {
        Map<String, String> webThreadContext = MDC.getCopyOfContextMap();
        logger.info("多线程执行任务...");
        return () -> {
            try {
                MDC.setContextMap(webThreadContext);
                task.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
