package com.donggl.common.config.swagger;

import cn.hutool.core.collection.CollUtil;
import org.springframework.core.env.Environment;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 访问接口地址：http://localhost:8081/上下文/swagger-ui/index.html
 *
 * @Api：用在类上，说明该类的作用。
 * @ApiOperation：注解来给API增加方法说明。
 * @ApiParam：定义在参数上
 * @ApiResponses：用于表示一组响应
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 l code：数字，例如400
 * l message：信息，例如"请求参数没填好"
 * l response：抛出异常的类
 * @ApiModel：描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候） l @ApiModelProperty：描述一个model的属性
 * @ApiImplicitParams: 用在方法上包含一组参数说明。
 * @ApiImplicitParam：用来注解来给方法入参增加说明。
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 创建API应用
     * <p>
     * apiInfo() 增加API相关信息
     * <p>
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * <p>
     * 指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */

    @Bean
    public Docket coreApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("adminApi")
                .select()
                //只显示admin下面的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理系统--api文档")
                .description("后台管理系统接口描述")
                .version("1.0")
                .contact(new Contact("测试", " http://baidu.com", "1102@qq.com"))
                .build();
    }

    @Bean(value = "adminApi")
    public Docket adminApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = CollUtil.newArrayList();
        parameterBuilder.name(CommonConstant.ADMIN_USER_ID).description("用户ID").modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        parameters.add(parameterBuilder.build());
        parameterBuilder.name(CommonConstant.ADMIN_USERNAME).description("工号").modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        parameters.add(parameterBuilder.build());
        parameterBuilder.name(CommonConstant.ADMIN_USER_FULL_NAME).description("姓名").modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        parameters.add(parameterBuilder.build());
        parameterBuilder.name(CommonConstant.ADMIN_TENANT_ID).description("租户ID").modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        parameters.add(parameterBuilder.build());
        parameterBuilder.name(CommonConstant.ADMIN_TENANT_CODE).description("租户编码").modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("后台管理接口").description("后台管理接口").version("1.0").build()).groupName("adminApi-1.0版本")
                //日期格式转换为字符串显示
                .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dongl.common.controller.test"))
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(parameters);
    }

    /**
     * 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
     **/
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }
    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
