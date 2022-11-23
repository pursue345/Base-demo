package com.donggl.common.common;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * @description: 全局异常处理类
 * @author donggl
 * @date 2022/11/10 14:29 
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理缺少参数异常
     * @param e 异常
     * @return Msg<Serializable>
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    Msg<Serializable> handleException(MissingServletRequestParameterException e){
        log.error(e.getMessage(), e);
        return Msg.error(ErrorCode.SYSTEM.MISSING_PARAM).message("缺少参数["+e.getParameterName()+"]").build();
    }

    /**
     * 处理所有业务异常
     * @param e 异常
     * @return Msg<Serializable>
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    Msg<Serializable> handleBusinessException(BizException e){
        if (e.getData()!=null&&!e.getData().isEmpty()) {
            log.error("error msg:{},error params:{}",e.getMessage(), JSON.toJSONString(e.getData()),e);
        }else {
            log.error("error msg:{}",e.getMessage(),e);
        }
        return Msg.error(e.getErrorCode()).build();
    }

    /**
     * 处理校验注解异常
     *
     * @param e 异常
     * @return 错误消息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Msg<String> handleBusinessException(MethodArgumentNotValidException e) {
        log.error( "error msg:{}", e.getMessage(), e);
        return new Msg<>(ErrorCode.SYSTEM.INVALID_PARAM.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
    }

    /**
     * 处理所有不可知的异常
     * @param e 异常
     * @return Msg<Serializable>
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    Msg<Serializable> handleException(Exception e){
        log.error(e.getMessage(), e);
        return Msg.error(ErrorCode.SYSTEM.SYSTEM_EXCEPTION).build();
    }
}

