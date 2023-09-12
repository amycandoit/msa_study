package com.example.pay.domain.response;

import com.example.pay.domain.dto.Customer;
import com.example.pay.domain.dto.Menu;
import com.example.pay.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class PaymentResponse {
    private Long id;
    private LocalDateTime createAt;
    private Integer price;
    private Long storeId;
    private List<Menu> menus;
    private Customer customer;

    public PaymentResponse(Payment payment, List<Menu> menus, Customer customer) {
        this.id = payment.getId();
        this.createAt = payment.getCreateAt();
        this.price = payment.getPrice();
        this.storeId = payment.getStoreId();
        this.menus = menus;
        this.customer = customer;
    }

}
