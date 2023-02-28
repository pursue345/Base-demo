package com.donggl.common.sensite.enumStyle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName 敏感信息序列化
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:46
 * @Version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
public class SensitiveSerialize extends JsonSerializer<String> implements ContextualSerializer {
    /**
     * 脱敏类型
     */
    private SensitiveTypeEnum sensitiveTypeEnum;


    /**
     * 前几位不脱敏
     */
    private Integer prefixNoMaskLen;

    /**
     * 最后几位不脱敏
     */
    private Integer suffixNoMaskLen;

    /**
     * 用什么打码
     */
    private String symbol;


    @Override
    public void serialize(final String origin, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        switch (sensitiveTypeEnum) {
            case CUSTOMER:
                jsonGenerator.writeString(DesensitizedUtils.desValue(origin, prefixNoMaskLen, suffixNoMaskLen, symbol));
                break;
            case NAME:
                jsonGenerator.writeString(DesensitizedUtils.chineseName(origin));
                break;
            case ID_NUM:
                jsonGenerator.writeString(DesensitizedUtils.idCardNum(origin));
                break;
            case PHONE_NUM:
                jsonGenerator.writeString(DesensitizedUtils.mobilePhone(origin));
                break;
            default:
                throw new IllegalArgumentException("unknown sensitive type enum " + sensitiveTypeEnum);
        }
    }


    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
                                              final BeanProperty beanProperty) throws JsonMappingException {
        Sensitive annotation = beanProperty.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, beanProperty.getType().getRawClass())) {
            this.sensitiveTypeEnum = annotation.type();
            this.prefixNoMaskLen = annotation.prefixNoMaskLen();
            this.suffixNoMaskLen = annotation.suffixNoMaskLen();
            this.symbol = annotation.symbol();
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }


}
