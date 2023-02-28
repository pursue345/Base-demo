package com.donggl.common.config.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName SwaggerDto
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/28 21:54
 * @Version 1.0
 */
@ApiModel(value = "Swagger测试类",description = "测试Swagger的类")
public class SwaggerDto implements Serializable {
    @ApiModelProperty(value = "swagger的主键")
    private String id;
    @ApiModelProperty(value = "swagger的name")
    private String name;
    @ApiModelProperty(value = "swagger的code")
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
