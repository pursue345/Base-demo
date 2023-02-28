package com.donggl.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.donggl.common.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author donggl
 * @since 2022-12-02
 */
public class LogDetail extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Long id;

    /**
     * 书名
     */
    private String name;

    /**
     * 描述
     */
    private String desc1;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private Integer status;

    private String deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc1;
    }

    public void setDesc(String desc1) {
        this.desc1 = desc1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "LogDetail{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc1 +
        ", type=" + type +
        ", status=" + status +
        ", deleted=" + deleted +
        "}";
    }
}
