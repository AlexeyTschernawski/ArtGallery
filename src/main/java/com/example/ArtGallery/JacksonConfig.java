//package com.example.ArtGallery;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        // Включите дополнительные настройки, если необходимо
//        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
//        return mapper;
//    }
//}