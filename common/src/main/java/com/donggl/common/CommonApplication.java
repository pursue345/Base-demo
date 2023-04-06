package com.donggl.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;


//禁用掉Spring Boot的数据源自动配置类，使用自定义数据源配置类完成数据源的初始化与管理
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
//开启Spring Boot 对AOP的支持
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableOpenApi
//重试注解
@EnableRetry
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

}
