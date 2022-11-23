package com.donggl.common.config.datasource.sqlsesion;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.donggl.common.mapper.mysql2", sqlSessionFactoryRef = "SqliteSqlSessionFactory")
public class MySQL2DataSourceConfig {

    @Bean(name = "SqliteSqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(
            @Qualifier("mysqlDataSource2") DataSource datasource) throws Exception {
        MybatisSqlSessionFactoryBean  bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/mysql2/*.xml"));
        return bean.getObject();
    }

    @Bean("SqliteSqlSessionTemplate")
    public SqlSessionTemplate test2SqlSessionTemplate(
            @Qualifier("SqliteSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    @Bean
    public PlatformTransactionManager transaction2Manager(@Qualifier("mysqlDataSource2")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}


