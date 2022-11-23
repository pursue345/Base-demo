package com.donggl.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.donggl.common.enums.InsertValid;
import com.donggl.common.enums.UpdateValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:产品实体类
 * @author donggl
 * @date 2022/11/10 14:39
 * @version 1.0
 */
@Data
public class ProductTopicEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主题主键id
     */
    @TableId(value = "product_topic_id", type = IdType.UUID)
    @NotBlank(groups = {UpdateValid.class}, message = "产品主题主键id不能为空")
    private String productTopicId;


    /**
     * 方法类型  0：事件，1：行为
     */
    @NotNull(groups = {InsertValid.class}, message = "方法类型不能为空")
    private Integer methodType;

    /**
     * 是否逻辑删除，0代表数据有效 1代表逻辑删除
     */
    private String deleted;
    @TableField(exist = false)
    private String methodKey;


    /**
     * 原topicId
     */
    @TableField(exist = false)
    private String sourceProductTopicId;

}








