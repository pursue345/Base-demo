package com.donggl.common.config.datasource.sqlsesion;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName MySQLDataSourceConfig
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/23 15:17
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.donggl.common.mapper.mysql1", sqlSessionFactoryRef = "MySQLSqlSessionFactory")
public class MySQL1DataSourceConfig {

    @Bean(name = "MySQLSqlSessionFactory")
    @Primary
    public SqlSessionFactory test1SqlSessionFactory(
            @Qualifier("mysqlDataSource1") DataSource datasource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean ();
        bean.setDataSource(datasource);
        bean.setMapperLocations(// 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/mysql1/*.xml"));
        return bean.getObject();
    }


    @Bean("MySQLSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate test1SqlSessionTemplate(
            @Qualifier("MySQLSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("mysqlDataSource1")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}

