package com.example.ordercommand.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String orderCommand = "order-command"; // customer 에 보낼 토픽
    public final static String orderQuery = "order-query";
    @Bean
    public NewTopic orderCommand() {
        return new NewTopic(orderCommand, 1, (short) 1);
    }

    @Bean
    public NewTopic orderQuery() {
        return TopicBuilder
                .name(orderQuery)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
