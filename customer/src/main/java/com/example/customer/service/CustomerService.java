package com.example.customer.service;

import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import com.example.customer.domain.request.CustomerRequest;
import com.example.customer.domain.request.SignupRequest;
import com.example.customer.domain.response.SignupResponse;
import com.example.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer saveCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity());
    }

    public SignupResponse checkSignup(TokenInfo tokenInfo) {
        Customer customer = getByToken(tokenInfo);
        return SignupResponse.builder()
                .customer(customer)
                .redirect(customer.getAddress()==null ? "/signup":"/main")
                .build();
    }
    @Transactional
    public SignupResponse signup(SignupRequest signupRequest, TokenInfo tokenInfo) {
        Customer customer = getByToken(tokenInfo);
        customer.setAddress(signupRequest.getAddress());
        return SignupResponse.builder()
                .customer(customer)
                .redirect("/main")
                .build();
    }

    public Customer getMe(TokenInfo tokenInfo) {
        return customerRepository.findById(tokenInfo.getId()).orElseThrow(() -> new IllegalArgumentException("NOT EXIST"));
    }

    public Customer getById(UUID id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("NOT EXIST"));
    }

    private Customer getByToken(TokenInfo tokenInfo) {
        UUID id = tokenInfo.getId();
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) {
            CustomerRequest customerRequest = new CustomerRequest(id, tokenInfo.getName(), tokenInfo.getNumber());
            return saveCustomer(customerRequest);
        } else {
            return byId.get();
        }
    }
}
