package com.donggl.common.service.impl;

import com.donggl.common.entity.User;
import com.donggl.common.mapper.mysql1.UserMapper;
import com.donggl.common.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author donggl
 * @since 2022-10-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Async("globalUserScoreExecutor")
    public void process() {
        logger.info("测试一下mdc");
    }
}
