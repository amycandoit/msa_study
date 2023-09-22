package com.example.auth.kafka;

import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String customerTopic = "customer"; // customer 에 보낼 토픽
    public final static String ownerTopic = "owner"; // owner 에 보낼 토픽

    @Bean
    public NewTopic customerTopic() {
        return new NewTopic(customerTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic ownerTopic() {
        return TopicBuilder
                .name(ownerTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
