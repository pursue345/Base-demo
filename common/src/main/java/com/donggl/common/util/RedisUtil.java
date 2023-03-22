package com.donggl.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author donggl
 * @Date 2023/3/22 21:25
 * @Version 1.0
 */
@Configuration
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;


    public Serializable getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <R extends Serializable> void set(String key, R result, int baseExpireAfterWrite) {

        redisTemplate.opsForValue().set(key,result,baseExpireAfterWrite);
    }
}
