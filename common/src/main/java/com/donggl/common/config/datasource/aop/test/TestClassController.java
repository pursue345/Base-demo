package com.donggl.common.config.datasource.aop.test;

import com.donggl.common.config.datasource.aop.DataSourceType;
import com.donggl.common.config.datasource.aop.TargetDataSource;
import com.donggl.common.entity.User;
import com.donggl.common.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserController
 * @Description 类：测试aop代理多数据源
 * @Author donggl
 * @Date 2022/11/23 14:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/aop/class")
// 将注解标注在类上，表示本类中所有的方法都是使用数据源1
@TargetDataSource(value = DataSourceType.MYSQL_DATASOURCE1)
public class TestClassController {
    @Resource
    private IUserService userService;

    @GetMapping(value = "/query")
    public List<User> queryUserList(){
        List<User> list = userService.list();
        return list;
    }

    @GetMapping(value = "/insert")
    public Boolean insertUserList(){
        User user = new User();
        return userService.save(user);
    }

}
