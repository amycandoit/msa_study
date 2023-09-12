package com.example.pay.domain.request;

import com.example.pay.config.TokenInfo;
import com.example.pay.domain.entity.Payment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequest {
    private final Integer price;
    private final Long storeId;
    private final String menuId;

    public Payment toEntity(TokenInfo tokenInfo) {
        return Payment.builder()
                .createAt(LocalDateTime.now())
                .customerId(tokenInfo.getId())
                .menuIds(menuId)
                .storeId(storeId)
                .price(price)
                .build();
    }
}

