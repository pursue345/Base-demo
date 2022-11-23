package com.donggl.common.common;

/**
 * @description: 错误码接口类
 * @author donggl
 * @date 2022/11/10 14:29
 * @version 1.0
 */
public interface IErrorCode {

    /**
     * 获取错误代码
     *
     * @return code
     */
    int getCode();
    /**
     * 获取错误描述信息
     *
     * @return 错误描述
     */
    String getMessage();
}

