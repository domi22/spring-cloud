package spring.cloud.service.test.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间序列化
 */
//@Configuration
public class JacksonConfig {

//    @Bean
//    @Primary
//    @ConditionalOnMissingBean
//    public ObjectMapper jacksonOnjectMapper(Jackson2ObjectMapperBuilder builder){
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        //将json属性的空值null转化为空字符串""
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
//                arg1.writeString("");
//            }
//        });
//        //json转换日期格式外传设置
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        SimpleModule simpleModule = new SimpleModule();
//        //在进出前后台的时候，设置BigDecimal和字符串之间转换
//        simpleModule.addSerializer(BigDecimal.class,ToStringSerializer.instance);
//        //设置对前台传入的多种格式的时间都支持序列化
//        simpleModule.addDeserializer(Date.class,CommonDateDeserializer.INSTANCE);
//        objectMapper.registerModule(simpleModule);
//        return objectMapper;
//    }
}
