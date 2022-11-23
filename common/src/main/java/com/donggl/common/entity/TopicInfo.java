package com.donggl.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 主题信息
 * </p>
 *
 * @author donggl
 * @since 2022-11-21
 */
@Data
@TableName("topic_info")
public class TopicInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主题主键id
     */
    @TableId(value = "product_topic_id", type = IdType.UUID)
    private String productTopicId;
    /**
     * 方法类型  0：事件，1：行为
     */
    @TableField("method_type")
    private Integer methodType;
    /**
     * topic类型  0：车端，1：应用
     */
    @TableField("topic_type")
    private Integer topicType;
    /**
     * 主题名字
     */
    @TableField("topic_name")
    private String topicName;
    /**
     * 订阅的主题规则
     */
    @TableField("sub_topic_rule")
    private String subTopicRule;
    /**
     * 发布的主题规则
     */
    @TableField("pub_topic_rule")
    private String pubTopicRule;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否逻辑删除，0代表数据有效 1代表逻辑删除
     */
    private String deleted;
    /**
     * 得分
     */
    private Integer score;


}
