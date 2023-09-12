package com.example.ordercommand.client.api;

import com.example.ordercommand.domain.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("ORDER-QUERY-SERVICE")
public interface OrderQueryClient {
    @PostMapping("/apt/v1/order")
    ResponseEntity<Void> save(Order order);
}
