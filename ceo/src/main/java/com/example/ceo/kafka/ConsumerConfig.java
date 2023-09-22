package com.example.ceo.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
public class ConsumerConfig {
    @Bean
    public RecordMessageConverter messageConverter() {
        return new JsonMessageConverter(); //byte로 받은거 json으로 바꿔줘
    }
}
