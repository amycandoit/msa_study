package com.example.pay.domain.kafka;

import lombok.Data;

@Data
public class OrderKafkaData {
    private Long storeId;
    private String customerId;
    private Integer price;
}
