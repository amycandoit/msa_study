package com.example.orderquery.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Order {
    @Id
    private UUID id;
    private Long storeId;
    private UUID customerId;
    private Integer price;
}
