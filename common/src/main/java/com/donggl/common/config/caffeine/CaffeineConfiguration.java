package com.donggl.common.config.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaffeineConfiguration
 * @Description Caffeine 缓存配置
 * @Author donggl
 * @Date 2022/11/22 14:41
 * @Version 1.0
 */
@Configuration
@EnableCaching
public class CaffeineConfiguration {
    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caffeineCaches = new ArrayList<>();

        for (CacheType cacheType : CacheType.values()) {
            Caffeine<Object, Object> cache = Caffeine.newBuilder();
            if(cacheType.getExpireAfterWrite()>0){
                cache.expireAfterWrite(cacheType.getExpireAfterWrite(),TimeUnit.SECONDS);
            }
            if(cacheType.getExpireAfterAccess()>0){
                cache.expireAfterAccess(cacheType.getExpireAfterAccess(),TimeUnit.SECONDS);
            }
            cache.maximumSize(cacheType.getMaxSize());
            caffeineCaches.add(new CaffeineCache(cacheType.toString(), cache.build()));
        }

        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }
}
