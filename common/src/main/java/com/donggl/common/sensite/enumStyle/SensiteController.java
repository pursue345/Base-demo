package com.donggl.common.sensite.enumStyle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SensiteController
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:54
 * @Version 1.0
 */
@RestController
@RequestMapping("sensite")
public class SensiteController {

    @GetMapping(value = "/query")
    @ResponseBody
    public TestSensiteDto queryUserList(){
        TestSensiteDto testSensiteDto = new TestSensiteDto();
        testSensiteDto.setName("王八蛋");
        testSensiteDto.setCardNo("500111199909099231");
        return testSensiteDto;
    }
}
