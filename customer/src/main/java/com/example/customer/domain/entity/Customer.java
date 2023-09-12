package com.example.customer.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Customer {
    @Id
    private UUID id;
    private String name;
    private String number;
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }
}
