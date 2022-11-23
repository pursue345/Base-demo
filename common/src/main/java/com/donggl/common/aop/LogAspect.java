package com.donggl.common.aop;


import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 日志切面，打印调用controller方法 入参和执行结果
 * @author donggl
 * @date 2022/11/10 12:11
 * @version 1.0
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.donggl.common.controller..*.*(..))")
    public void log(){}

    @Around("log()")
    public Object doBefore(ProceedingJoinPoint point) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName();
        Object[] args = point.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestLog);
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long end = System.currentTimeMillis();
        logger.info("执行耗时：{}",end-start + "ms");
        return result;
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void logAfterReturning(Object result){
        logger.info("执行完成,{}", JSON.toJSONString(result));

    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
