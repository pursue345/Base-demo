package com.donggl.common.config.swagger;

import com.donggl.common.common.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SwaggerController
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/28 21:16
 * @Version 1.0
 */
@RestController
@RequestMapping("swagger")
@Api(value = "swagger的controller层")
public class SwaggerController {

    @PostMapping("/test")
    @ResponseBody
    @ApiOperation(value = "测试一下swagger而已啦",notes = "测试一把")
    public Msg<SwaggerDto> relationApp() {

        SwaggerDto swaggerDto = new SwaggerDto();
        swaggerDto.setCode("1");
        swaggerDto.setName("测试一把");
        swaggerDto.setId("主键测试");
        return Msg.success(swaggerDto).build();
    }
}
