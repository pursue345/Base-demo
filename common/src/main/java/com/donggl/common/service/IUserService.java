package com.donggl.common.service;

import com.donggl.common.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author donggl
 * @since 2022-10-25
 */
public interface IUserService extends IService<User> {

    void process();
}
