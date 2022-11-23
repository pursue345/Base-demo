package com.donggl.common.buffer;

import com.donggl.common.entity.TopicInfo;
import com.donggl.common.service.ITopicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TopicInfoPublisher
 * @Description 缓冲处理
 * @Author donggl
 * @Date 2022/11/21 17:17
 * @Version 1.0
 */

@Component
public class TopicInfoPublisher extends AbstractPublisher<TopicInfo> {
    @Lazy
    @Autowired
    private ITopicInfoService topicInfoService;
    public TopicInfoPublisher(@Value("${publish.global.buffSize:100}") int buffSize,
                              @Value("${publish.global.buffSize:5}") long delay) {
        super(buffSize, 10, delay, TimeUnit.SECONDS);
    }

    @Override
    public void publish(List<TopicInfo> dataList) {
        topicInfoService.batchUpdate(dataList);
    }
}

