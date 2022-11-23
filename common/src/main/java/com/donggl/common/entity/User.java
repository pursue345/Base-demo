package com.donggl.common.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.donggl.common.enums.EnumValue;
import com.donggl.common.enums.InsertValid;
import com.donggl.common.enums.UpdateValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 用户实体类
 * @author donggl
 * @date 2022/11/10 14:39
 * @version 1.0
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "id", orderNum = "0", width = 10, isImportField = "true")
    private Integer id;

    /**
     * 姓名
     */
    @NotBlank(groups = {InsertValid.class, UpdateValid.class}, message = "name不能为空")
    @Length(groups = {InsertValid.class, UpdateValid.class}, message = "名称超过限定长度50", max = 50)
    @Excel(name = "姓名", orderNum = "1", width = 10, isImportField = "true")
    private String name;

    /**
     * 年龄
     */
    @NotNull(groups = {InsertValid.class}, message = "age不能为空")
    @Excel(name = "年龄", orderNum = "2", width = 10, isImportField = "true")
    private Integer age;

    /**
     * 类型
     */
    @NotBlank(groups = {InsertValid.class}, message = "type不能为空")
    @EnumValue(strValues = {"1", "2"})
    private String type;

    /**
     * 是否逻辑删除  0代表数据有效 1代表逻辑删除
     */
    @TableLogic(delval = "1")
    private String deleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
