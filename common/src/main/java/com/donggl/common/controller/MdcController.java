package com.donggl.common.controller;

import com.donggl.common.common.BizException;
import com.donggl.common.common.ErrorCode;
import com.donggl.common.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: mdc日志链路控制层
 * @author donggl
 * @date 2022/11/10 14:38 
 * @version 1.0
 */
@RestController
@RequestMapping("/mdc")
public class MdcController {
    private static final Logger logger = LoggerFactory.getLogger(MdcController.class);


    @Autowired
    private IUserService userService;

    @PostMapping("/test")
    @ResponseBody
    public String testMdc(String name,String age) {
        logger.info("测试mdc");
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            throw new BizException(ErrorCode.SYSTEM.BIZ_ERROR);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userService.process();
        return "1";
    }

}
