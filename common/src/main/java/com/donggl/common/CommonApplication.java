package com.donggl.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


//禁用掉Spring Boot的数据源自动配置类，使用自定义数据源配置类完成数据源的初始化与管理
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
//开启Spring Boot 对AOP的支持
@EnableAspectJAutoProxy
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

}
