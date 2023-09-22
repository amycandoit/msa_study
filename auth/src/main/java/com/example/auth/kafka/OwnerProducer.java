package com.example.auth.kafka;

import com.example.auth.client.request.OwnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerProducer {
    private final KafkaTemplate<String, OwnerRequest> kafkaTemplate;

    public void send(OwnerRequest ownerRequest) {
        kafkaTemplate.send(TopicConfig.ownerTopic, ownerRequest);
    }
}
