package com.donggl.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @description: mybatis-plus默认值设置
 * @author donggl
 * @date 2022/11/10 14:38
 * @version 1.0
 */
@Component

public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createAt", new Date(), metaObject);
        this.setFieldValByName("updateAt", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //wrapper进行修改操作时不会生效
        this.setFieldValByName("updateAt", new Date(), metaObject);
    }
}





