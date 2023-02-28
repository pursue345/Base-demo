package com.donggl.common.config.redis;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.spring.data.connection.RedissonConnectionFactory;
//import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedissonConfig
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/21 14:11
 * @Version 1.0
 */
@Configuration
//@ImportAutoConfiguration(RedisRedissonProperties.class)
public class RedissonConfig {

    private static final String PREFIX = "redis://";

    /*@Bean
    public RedissonClient redisson(RedisRedissonProperties redisRedissonProperties) throws IOException {
        Config config = Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream());
        List<String> clusterNodes = redisRedissonProperties.getCluster().getNodes();
        if (CollUtil.isNotEmpty(clusterNodes)) {
            for (String clusterNode : clusterNodes) {
                config.useClusterServers().addNodeAddress(PREFIX + clusterNode);
            }
        }
        return Redisson.create(config);
//        Config config1 = new Config();
//        // 添加集群地址
//        ClusterServersConfig clusterServersConfig = config1.useClusterServers()
//                .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()])).setReadMode(ReadMode.MASTER);
//        return Redisson.create(config1);
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }*/


    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redissonConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redissonConnectionFactory);

        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("com.donggl");
        redisTemplate.setEnableDefaultSerializer(false);

        //使用fastjson序列化，是否可以使用kyro序列化
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

        // value值的序列化采用fastJsonRedisSerializer
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}


