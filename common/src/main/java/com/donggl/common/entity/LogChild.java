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
public class LogChild extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private Long recordId;

    /**
     * code
     */
    private String code;

    /**
     * 名称
     */
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LogChild{" +
        "id=" + id +
        ", recordId=" + recordId +
        ", code=" + code +
        ", name=" + name +
        "}";
    }
}
