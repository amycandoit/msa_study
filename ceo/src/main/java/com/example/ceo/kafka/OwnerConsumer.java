package com.example.ceo.kafka;

import com.example.ceo.domain.request.OwnerRequest;
import com.example.ceo.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OwnerConsumer {
    private final OwnerService ownerService;

    @KafkaListener(topics = TopicConfig.ownerTopic)
    public void listen(OwnerRequest ownerRequest) {
        ownerService.ownerSave(ownerRequest); //kafka로 받아서 저장해서 더이상 controller의 post가(save) 필요없음
    }
}
