package br.com.tiagooliveira.medicalconsult.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objects(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GNT-3"));
        objectMapper.setDateFormat(dateFormat);

        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);

        return objectMapper;
    }
}