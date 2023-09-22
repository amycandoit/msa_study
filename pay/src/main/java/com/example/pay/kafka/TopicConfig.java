package com.example.pay.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String orderCommand = "order-command"; // customer 에 보낼 토픽

    @Bean
    public NewTopic orderCommand() {
        return new NewTopic(orderCommand, 1, (short) 1);
    }

//    @Bean
//    public NewTopic ownerTopic() {
//        return TopicBuilder
//                .name(ownerTopic)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
}
