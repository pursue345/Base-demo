package com.donggl.common.config.datasource.aop;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DataSourceManagement
 * @Description 将数据源注入
 * @Author donggl
 * @Date 2022/11/23 14:26
 * @Version 1.0
 */
@Primary
@Component
public class DataSourceManagement extends AbstractRoutingDataSource {

    public static ThreadLocal<String> flag = new ThreadLocal<>();

    @Resource
    private DataSource mysqlDataSource1;

    @Resource
    private DataSource mysqlDataSource2;

    public DataSourceManagement(){
        flag.set(DataSourceType.MYSQL_DATASOURCE1.name());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return flag.get();
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object,Object> targetDataSource = new ConcurrentHashMap<>();
        targetDataSource.put(DataSourceType.MYSQL_DATASOURCE1.name(),mysqlDataSource1);
        targetDataSource.put(DataSourceType.MYSQL_DATASOURCE2.name(),mysqlDataSource2);
        super.setTargetDataSources(targetDataSource);
        super.setDefaultTargetDataSource(mysqlDataSource1);
        super.afterPropertiesSet();
    }
}

