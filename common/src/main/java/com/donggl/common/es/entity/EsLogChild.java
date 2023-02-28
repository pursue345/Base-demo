package com.donggl.common.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @ClassName EsLogChild
 * @Description TODO
 * @Author donggl
 * @Date 2022/12/2 10:00
 * @Version 1.0
 */
public class EsLogChild implements Serializable {

    @Field(type = FieldType.Text)
    private Long recordId;

    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String name;

    @Field(type = FieldType.Text)
    private String code;

    public EsLogChild() {
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
