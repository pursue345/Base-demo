package com.donggl.common.config.datasource.aop;

import java.lang.annotation.*;

/**
 * @ClassName TargetDataSource
 * @Description 自定义注解
 * @Author donggl
 * @Date 2022/11/23 14:27
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    DataSourceType value() default DataSourceType.MYSQL_DATASOURCE1;
}

