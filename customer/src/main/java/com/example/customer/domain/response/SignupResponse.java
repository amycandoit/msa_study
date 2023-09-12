package com.example.customer.domain.response;

import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private Customer customer;
    private String redirect;
}
