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
 * @since 2022-11-23
 */
public class Type extends BaseEntity {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 竞赛类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 1已删除  0未删除
     */
    private Integer del;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "Type{" +
        "id=" + id +
        ", type=" + type +
        ", remark=" + remark +
        ", del=" + del +
        "}";
    }
}
