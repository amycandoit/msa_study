package com.example.pay.kafka;


import com.example.pay.domain.kafka.OrderKafkaData;
import com.example.pay.kafka.TopicConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandProducer {
    private final KafkaTemplate<String, OrderKafkaData> kafkaTemplate;

    public void send(OrderKafkaData orderKafkaData) {
        kafkaTemplate.send(TopicConfig.orderCommand, orderKafkaData);
    }


}
