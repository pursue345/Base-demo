package com.donggl.common.batch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CarConditionScheduleExecutorConfig
 * @Description TODO
 * @Author donggl
 * @Date 2023/3/22 21:44
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "car.condition-schedule")
public class CarConditionScheduleExecutorConfig {

    /**
     * 一次批量存储数据大小
     */
    private Integer batchSize;
    /**
     * 守护线程检查时间间隔，单位为毫秒，默认取10秒
     */
    private Integer batchCheckInterval;
    /**
     * 最大队列大小
     */
    private Integer maxQueueSize;
    /**
     * 连接池配置
     */
    private JedisPoolConfig jedisPoolConfig;
}
