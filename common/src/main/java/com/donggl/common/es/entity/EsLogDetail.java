package com.donggl.common.es.entity;

import cn.hutool.core.collection.CollUtil;
import com.donggl.common.es.entity.EsLogChild;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: es日志操作类
 * @author donggl
 * @date 2022/12/2 10:07
 * @version 1.0
 */
@Document(indexName = "log_detail",createIndex = true)
public class EsLogDetail implements Serializable {

    /**
     * date、float、long都是不可被拆分
     * FieldType.Text analyzer = "ik_smart" 字段是文本，作最小切分
     * FieldType.Text analyzer = "ik_max_word" 字段是文本，作最细粒度划分
     * FieldType.Text index=false 字段是文本，不建立索引
     * FieldType.Date 字段是文本，日期类型，默认不建立索引
     * FieldType.Long 字段是长整型，默认建立索引
     * FieldType.Keyword 字段是文本，整体不可分，默认建立索引
     * FieldType.Float 字段是浮点类型，整体不可分，默认建立索引
     * analyzer：索引时
     * searchAnalyzer：查询时
     */
    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    /**
     * 书名
     */
    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String name;

    /**
     * 描述
     */
    @Field(type = FieldType.Text)
    private String desc1;

    /**
     * 类型
     */
    @Field(type = FieldType.Text)
    private String type;

    /**
     * 状态
     */
    @Field(type = FieldType.Long)
    private Integer status;

    /**
     * 是否删除
     */
    @Field(type = FieldType.Text)
    private String deleted;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date createAt;
    /**
     * 创建人
     */
    @Field(type = FieldType.Text)
    private String createBy;
    /**
     * 更新时间
     */
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateAt;
    /**
     * 更新人
     */
    @Field(type = FieldType.Text)
    private String updateBy;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * list
     */
    @Field(type = FieldType.Nested)
    private List<EsLogChild> esLogChildList= CollUtil.newArrayList();

    /**
     * timestamp
     */
    @Field(type = FieldType.Long)
    private Long timestamp;

    public EsLogDetail() {
    }

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

    public String getDesc1() {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<EsLogChild> getEsLogChildList() {
        return esLogChildList;
    }

    public void setEsLogChildList(List<EsLogChild> esLogChildList) {
        this.esLogChildList = esLogChildList;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EsLogDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc1 + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", deleted='" + deleted + '\'' +
                ", createAt=" + createAt +
                ", createBy='" + createBy + '\'' +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                ", price=" + price +
                ", esLogChildList=" + esLogChildList +
                '}';
    }
}
