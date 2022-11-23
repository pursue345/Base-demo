package com.donggl.common.config.datasource.aop.test;

import com.donggl.common.config.datasource.aop.DataSourceType;
import com.donggl.common.config.datasource.aop.TargetDataSource;
import com.donggl.common.entity.Type;
import com.donggl.common.entity.User;
import com.donggl.common.service.ITypeService;
import com.donggl.common.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TestMethodController
 * @Description 方法：测试aop代理多数据源
 * @Author donggl
 * @Date 2022/11/23 14:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/aop/method")
public class TestMethodController {

    @Resource
    private IUserService userService;

    @Resource
    private ITypeService typeService;

    @GetMapping(value = "/query")
    @TargetDataSource(value = DataSourceType.MYSQL_DATASOURCE1)
    public List<User> queryUserList(){
        List<User> list = userService.list();
        return list;
    }

    @GetMapping(value = "/insert")
    @TargetDataSource(value = DataSourceType.MYSQL_DATASOURCE2)
    public Boolean insertUserList(){
        Type type = new Type();
        return typeService.save(type);
    }
}
