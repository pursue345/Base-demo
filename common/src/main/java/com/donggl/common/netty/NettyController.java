package com.donggl.common.netty;

import com.donggl.common.netty.client.HelloWorldClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName NettyController
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:20
 * @Version 1.0
 */
@RestController
@RequestMapping("netty")
public class NettyController {
    @Resource
    private HelloWorldClient helloWorldClient;

    @GetMapping(value = "/query")
    public void queryUserList(String msg){
        helloWorldClient.send(msg);
    }
}
