package com.donggl.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/23 14:20
 * @Version 1.0
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "mysqlDataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.mysql-datasource1")
    public DataSource dataSource1(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "mysqlDataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.mysql-datasource2")
    public DataSource dataSource2(){
        return DruidDataSourceBuilder.create().build();
    }
}

