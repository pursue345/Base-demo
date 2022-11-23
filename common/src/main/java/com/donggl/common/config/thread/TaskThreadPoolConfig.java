package com.donggl.common.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 线程池读取配置文件类
 * @author donggl
 * @date 2022/11/10 14:37
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "pool")
@Component

public class TaskThreadPoolConfig {

    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveSeconds;
    private int queueCapacity;

}
