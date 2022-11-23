package com.donggl.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.donggl.common.entity.TopicInfo;

import java.util.List;

/**
 * @ClassName ITopicInfoService
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/21 17:12
 * @Version 1.0
 */
public interface ITopicInfoService extends IService<TopicInfo> {

    void loadDataToRedis();

    Double getScore(String key);

    void addScore(String key,Integer score);

    Integer getRank(String key);

    void copyRedis2Mysql(Integer pageSize);

    void batchUpdate(List<TopicInfo> dataList);

}
