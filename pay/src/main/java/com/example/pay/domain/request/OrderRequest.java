package com.example.pay.domain.request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long storeId;
    private String customerId;
    private Integer price;

}
