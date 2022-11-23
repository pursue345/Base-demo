package com.donggl.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.donggl.common.common.Msg;
import com.donggl.common.log.LoggerUtil;
import com.donggl.common.service.ITopicInfoService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @ClassName RedisController
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/21 17:08
 * @Version 1.0
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private ITopicInfoService iTopicInfoService;

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    //加载数据到redis
    @GetMapping("loadDataToRedis")
    public Msg<String> loadDataToRedis() {
        iTopicInfoService.loadDataToRedis();
        return Msg.success("true").build();
    }

    //根据传入key获取分数
    @GetMapping("getScore")
    public Msg<Double> getScore(@NotBlank String key) {
        return Msg.success(iTopicInfoService.getScore(key)).build();
    }


    //为某一个key加分数
    @GetMapping("addScore")
    public Msg<String> addScore(@NotBlank String key, @NotNull Integer score) {
        iTopicInfoService.addScore(key, score);
        return Msg.success("").build();
    }


    //获取倒叙排名
    @GetMapping("getRank")
    public Msg<Integer> getRank(@NotBlank String key) {
        return Msg.success(iTopicInfoService.getRank(key)).build();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    //获取zSet分数
    @GetMapping("getzSetScore")
    public Msg<Double> getzSetScore(@NotBlank String key, @NotBlank String eKey) {
        return Msg.success(redisTemplate.opsForZSet().score(key, eKey)).build();
    }

    //根据key删除redis数据
    @PostMapping("removeByKey")
    public Msg<Boolean> removeByKey(@NotBlank String key) {
        return Msg.success(redisTemplate.delete(key)).build();
    }

    //获取zSet元素
    @GetMapping("getzSetMember")
    public Msg<String> getzSetMember(@NotBlank String key) {
        Set range = redisTemplate.opsForZSet().range(key, 0, -1);
        return Msg.success(JSONObject.toJSONString(range)).build();
    }

    //获取redis相关key 正则表达式
    @GetMapping("getRedisKeys")
    public Msg<String> getRedisKeys(@NotBlank String pattern) {
        Set keys = redisTemplate.keys(pattern);
        return Msg.success(JSONObject.toJSONString(keys)).build();
    }

    //redis 到 mysql
    @PostMapping("redisCopy2Mysql")
    public void redisCopy2Mysql(@RequestParam(defaultValue = "1000") Integer pageSize) {
        iTopicInfoService.copyRedis2Mysql(pageSize);
    }

    /*@Autowired
    private RedissonClient redissonClient;
    @PostMapping("redission")
    public void redission(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            //获取锁
            lock.lock();
            LoggerUtil.info(logger, "Redisson锁 {}", key);
        } catch (Exception e) {
            LoggerUtil.error(logger, "处理异常 {}", key, e);
            throw e;
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                //释放锁
                lock.unlock();
                LoggerUtil.info(logger, "Redisson锁释放 {}", key);
            }
        }
    }*/
}
