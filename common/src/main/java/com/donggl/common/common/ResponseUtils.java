package com.donggl.common.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 响应
 * @author donggl
 * @date 2022/11/10 14:36
 * @version 1.0
 */
public class ResponseUtils {

    public static HttpServletResponse getResponse(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }
}
