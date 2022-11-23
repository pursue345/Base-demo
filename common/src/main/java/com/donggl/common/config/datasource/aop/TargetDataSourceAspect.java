package com.donggl.common.config.datasource.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName TargetDataSourceAspect
 * @Description 注解实现类
 * @Author donggl
 * @Date 2022/11/23 14:28
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class TargetDataSourceAspect {

    @Before("@within(com.donggl.common.config.datasource.aop.TargetDataSource) || @annotation(com.donggl.common.config.datasource.aop.TargetDataSource)")
    public void beforeNoticeUpdateDataSource(JoinPoint joinPoint){
        TargetDataSource annotation = null;
        Class<? extends Object> target = joinPoint.getTarget().getClass();
        if(target.isAnnotationPresent(TargetDataSource.class)){
            // 判断类上是否标注着注解
            annotation = target.getAnnotation(TargetDataSource.class);
            log.info("类上标注了注解");
        }else{
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            if(method.isAnnotationPresent(TargetDataSource.class)){
                // 判断方法上是否标注着注解，如果类和方法上都没有标注，则报错
                annotation = method.getAnnotation(TargetDataSource.class);
                log.info("方法上标注了注解");
            }else{
                throw new RuntimeException("@TargetDataSource注解只能用于类或者方法上, 错误出现在:[" +
                        target.toString() +" " + method.toString() + "];");
            }
        }
        // 切换数据源
        DataSourceManagement.flag.set(annotation.value().name());
    }



    /**
     * @description:
     * @Before前置通知，@Around包含@Before
     * @Around 环绕通知，环绕通知需要执行joinPoint.process()方法来调用目标对象的方法，最后返回执行的值
     * ProceedingJoinPoint 对象只能在@Around环绕通知中使用，在其他通知中使用就会报错
     * @author donggl
     * @date 2022/11/23 14:31
     * @version 1.0
     */
/*    @Around("@within(TargetDataSource) || @annotation(TargetDataSource)")
    public Object beforeNoticeUpdateDataSource(ProceedingJoinPoint joinPoint){
        // 省略逻辑代码
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }*/


}

