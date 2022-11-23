package com.donggl.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.donggl.common.buffer.TopicInfoPublisher;
import com.donggl.common.entity.TopicInfo;
import com.donggl.common.mapper.TopicInfoMapper;
import com.donggl.common.service.ITopicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @ClassName TopicInfoServiceImpl
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/21 17:13
 * @Version 1.0
 */
@Service
public class TopicInfoServiceImpl extends ServiceImpl<TopicInfoMapper, TopicInfo> implements ITopicInfoService {

    public static final String TOPIC_INFO = "TOPICINFO:score";


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ITopicInfoService topicInfoService;

    @Autowired
    private TopicInfoPublisher topicInfoPublisher;

    @Override
    public void loadDataToRedis() {
        QueryWrapper<TopicInfo> query = Wrappers.query();
        List<TopicInfo> list = list(query);
        Set<ZSetOperations.TypedTuple<Serializable>> sets = new HashSet<>();
        list.forEach(topicInfo -> {
            ZSetOperations.TypedTuple<Serializable> typedTuple = new DefaultTypedTuple<Serializable>(topicInfo.getProductTopicId(), topicInfo.getScore().doubleValue());
            sets.add(typedTuple);
        });
        if (!CollUtil.isEmpty(sets)) {
            loadRedis(sets);
        }
    }

    @Override
    public Double getScore(String key) {
        Double score = redisTemplate.opsForZSet().score(TOPIC_INFO, key);
        logger.info("查询车key:{},score:{}", key, score);
        return score;
    }

    @Override
    public void addScore(String key, Integer score) {
        try {
            redisTemplate.opsForZSet().incrementScore(TOPIC_INFO, key, score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getRank(String key) {
        int resultRank = 0;
        Long rank = redisTemplate.opsForZSet().reverseRank(TOPIC_INFO, key);
        if (null != rank) {
            resultRank = Math.toIntExact(rank + 1);
        }
        logger.info("查询车系数据getRank结束,key:{},resultRank：{}", key, resultRank);
        return resultRank;
    }

    //加载数据到redis
    private void loadRedis(Set<ZSetOperations.TypedTuple<Serializable>> sets) {
        logger.info("加载全局数据到redis,size:{}", sets.size());
        try {
            redisTemplate.opsForZSet().add(TOPIC_INFO, sets);
        } catch (Exception e) {
            logger.info("加载全局数据到redis异常,key:{},sessionKey:{}", TOPIC_INFO, sets.size(), e);
        }
        logger.info("加载全局数据到redis结束,key:{},sessionKey:{}", TOPIC_INFO, sets.size());
    }

    @Override
    public void copyRedis2Mysql(Integer pageSize) {
        logger.info("更新全局数据到mysql开始...");
        Long size = redisTemplate.opsForZSet().size(TOPIC_INFO);
        if (null != size && size.intValue() > 0) {
            processRedis2Mysql(0, pageSize, size.intValue(), pageSize);
        }
        logger.info("更新全局数据到mysql结束...");
    }

    @Override
    public void batchUpdate(List<TopicInfo> dataList) {
        if(!CollUtil.isEmpty(dataList)){
            try {
                topicInfoService.saveOrUpdateBatch(dataList, dataList.size());
            } catch (Exception e) {
                logger.error("全局驾驶分数batchUpdate出错, size:{}, list{}", dataList.size(), dataList, e);
            }
        }
    }

    private void processRedis2Mysql(int start, int end, int size, int pageSize) {
        logger.info("processRedis2Mysql 更新全局数据到mysql开始...start:{}, end:{}, size:{}", start, end, size);
        if (start >= size) {
            logger.info("processRedis2Mysql 更新全局数据到mysql结束...start:{}, end:{}, size:{}", start, end, size);
            return;
        }

        Set<ZSetOperations.TypedTuple<Serializable>> set =
                redisTemplate.opsForZSet().rangeWithScores(TOPIC_INFO, start, end);
        if (null == set) {
            logger.info("copyRedis2Mysql未获取到全局分数数据");
            return;
        }

        for (ZSetOperations.TypedTuple<Serializable> typedTuple : set) {
            String key = (String) typedTuple.getValue();
            if (StrUtil.isEmpty(key)) {
                continue;
            }
            Double score = typedTuple.getScore();
            TopicInfo topicInfo = new TopicInfo();
            topicInfo.setProductTopicId(key);
            topicInfo.setScore(score.intValue());
            topicInfoPublisher.push(topicInfo);
        }

        start = end;
        end = start + pageSize;
        this.processRedis2Mysql(start, end, size, pageSize);
    }

}
