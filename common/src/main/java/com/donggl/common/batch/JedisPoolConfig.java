package com.donggl.common.batch;

import lombok.Data;

/**
 * @ClassName JedisPoolConfig
 * @Description TODO
 * @Author donggl
 * @Date 2023/3/22 21:45
 * @Version 1.0
 */
@Data
public class JedisPoolConfig {

    /**
     * 最大空闲连接数
     */
    private Integer maxIdle;
    /**
     * 最大连接数
     */
    private Integer maxTotal;
    /**
     * 最小空闲连接数
     */
    private Integer minIdle;
    /**
     * 最长等待时间
     */
    private Integer maxWaitMIIlls;
}
