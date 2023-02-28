package com.donggl.common.config.datasource.sqlsesion.test;

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
 * @ClassName TestSqlSessionController
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/23 16:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/sqlsession/ds")
public class TestSqlSessionController {
    @Resource
    private IUserService userService;

    @Resource
    private ITypeService typeService;

    @GetMapping(value = "/query")
    public List<User> queryUserList(){
        List<User> list = userService.list();
        return list;
    }

    @GetMapping(value = "/insert")
    public Boolean insertUserList(){
        Type type = new Type();
        type.setType("1");
        return typeService.save(type);
    }
}
