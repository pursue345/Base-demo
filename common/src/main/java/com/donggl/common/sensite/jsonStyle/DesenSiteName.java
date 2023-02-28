package com.donggl.common.sensite.jsonStyle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
/**
 * @ClassName DesenSiteName
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 22:57
 * @Version 1.0
 */
//字段上加注解：@JsonSerialize(using = DesenSiteName.class)
public class DesenSiteName extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (StringUtils.isNotBlank(value)) {
            String name = StringUtils.right(value, 1);
            String text = StringUtils.leftPad(name, StringUtils.length(value), "*");
            gen.writeString(text);
        }
    }

}
