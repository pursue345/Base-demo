package com.donggl.common.controller;

import com.alibaba.fastjson.JSON;
import com.donggl.common.config.caffeine.CacheType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaffeineController
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/22 14:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/caffeine")
public class CaffeineController {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @description:  测试caffeine和redis实现二级缓存
     * @author donggl
     * @date 2022/11/22 14:50
     * @version 1.0
     */
    @PostMapping("/cache")
    @ResponseBody
    public void cache(String key) {
        //查询caffeine本地缓存
        String caffeineKey = "caffeine:" + key;
        Cache cache = cacheManager.getCache(CacheType.USER_CAR.toString());
        Cache.ValueWrapper cacheData = cache.get(caffeineKey);
        if(!Objects.isNull(cacheData)){
            String CacheCarId = (String) cacheData.get();
            if(StringUtils.isNotBlank(CacheCarId)){
                System.out.println("caffine:key:" + caffeineKey + ",value:" + CacheCarId);
                return;
            }
        }
        String redisKey = "redis:" + key;
        Object obj = redisTemplate.opsForValue().get(redisKey);
        if (!ObjectUtils.isEmpty(obj)) {
            String strValue = JSON.toJSONString(obj);
            System.out.println("redis:key:" + caffeineKey + ",value:" + strValue);
            return;
        }
        //查询数据库
        String value = "value大哥";
        cache.put(caffeineKey,value);
        redisTemplate.opsForValue().set(redisKey,value,120, TimeUnit.SECONDS);
        System.out.println("新增caffeine和redis缓存");
    }
}
