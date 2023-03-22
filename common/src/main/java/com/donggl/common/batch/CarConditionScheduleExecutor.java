//package com.donggl.common.batch;
//
//import com.google.common.collect.Lists;
//import io.lettuce.core.LettuceFutures;
//import io.lettuce.core.RedisFuture;
//import io.lettuce.core.cluster.RedisClusterClient;
//import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
//import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
//import io.lettuce.core.codec.ByteArrayCodec;
//
//import io.lettuce.core.support.ConnectionPoolSupport;
//import org.apache.commons.pool2.impl.GenericObjectPool;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName CarConditionScheduleExecutor
// * @Description TODO
// * @Author donggl
// * @Date 2023/3/22 21:42
// * @Version 1.0
// */
//
//@Component
//public class CarConditionScheduleExecutor extends AbstractScheduleExecutor<CarConditionAddDto>{
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//    private final GenericObjectPool<StatefulRedisClusterConnection<byte[], byte[]>> pool;
//
//    /**
//     *
//     * @param redisClusterClient
//     * @param executorConfig
//     */
//    public CarConditionScheduleExecutor(@Qualifier("redisClusterClient") RedisClusterClient redisClusterClient,
//                                        @Qualifier("carConditionScheduleExecutorConfig") CarConditionScheduleExecutorConfig executorConfig) {
//        super(executorConfig.getBatchSize(), executorConfig.getBatchCheckInterval(), executorConfig.getMaxQueueSize());
//        GenericObjectPoolConfig<StatefulRedisClusterConnection<byte[], byte[]>> poolConfig = new GenericObjectPoolConfig();
//        final JedisPoolConfig jedisPoolConfig = executorConfig.getJedisPoolConfig();
//        poolConfig.setMaxIdle(jedisPoolConfig.getMaxIdle());
//        poolConfig.setMaxTotal(jedisPoolConfig.getMaxTotal());
//        poolConfig.setMinIdle(jedisPoolConfig.getMinIdle());
//        poolConfig.setMaxWaitMillis(jedisPoolConfig.getMaxWaitMIIlls());
//        poolConfig.setTestOnBorrow(true);
//        this.pool = ConnectionPoolSupport
//                .createGenericObjectPool(() -> redisClusterClient.connect(new ByteArrayCodec()), poolConfig);
//    }
//
//    @Override
//    protected void execute(List<CarConditionAddDto> list) {
//        if (null == list || list.size() == 0) {
//            return;
//        }
//        StatefulRedisClusterConnection<byte[], byte[]> connection = null;
////        StatefulRedisClusterConnection<String, String> connection1 = null;
//        try {
//            final RedisSerializer keySerializer = redisTemplate.getKeySerializer();
//            final RedisSerializer hashValueSerializer = redisTemplate.getHashValueSerializer();
//            connection = pool.borrowObject();
//            RedisAdvancedClusterAsyncCommands<byte[], byte[]> commonds = connection.async();
//
////            RedisAdvancedClusterAsyncCommands<String, String> commonds1 = connection1.async();
//            List<RedisFuture<?>> futures = Collections.synchronizedList(Lists.newArrayList());
//            list.parallelStream().forEach(eventDataDto -> {
//                final String carId = eventDataDto.getCarId();
//                final HashMap<String, Object> params = eventDataDto.getParams();
//                final byte[] key = keySerializer.serialize("CAR:CONDITION:" + carId);
//                Map<byte[], byte[]> conditionMap = new LinkedHashMap(params.size());
//                final Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    final Map.Entry next = iterator.next();
//                    conditionMap.put(keySerializer.serialize(next.getKey()), hashValueSerializer.serialize(next.getValue()));
//                }
//                // 压缩
////                String compress = RedisCompressUtils.compress("123");
//                // 数据解压缩
////                String output = RedisCompressUtils.decompress(compress);
//                futures.add(commonds.hmset(key, conditionMap));
////                futures.add(commonds1.setex("123", 123, "value"));
//                logger.info("车况存入成功，carid:{}", carId);
//            });
//            LettuceFutures.awaitAll(1, TimeUnit.SECONDS,
//                    futures.toArray(new RedisFuture[futures.size()]));
//        } catch (Exception e) {
//            logger.error("车况批量写Redis处理异常:", e);
//        } finally {
//            pool.returnObject(connection);
//        }
//    }
//}
