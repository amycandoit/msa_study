package com.example.customer.contoroller;

import com.example.customer.config.JwtService;
import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import com.example.customer.domain.request.CustomerRequest;
import com.example.customer.domain.request.SignupRequest;
import com.example.customer.domain.response.SignupResponse;
import com.example.customer.service.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final JwtService jwtService;

    @PostMapping("check")
    public SignupResponse check(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        return customerService.checkSignup(tokenInfo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CustomerRequest customerRequest) {
        customerService.saveCustomer(customerRequest);
    }

    @GetMapping("me")
    public Customer getMe(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        return customerService.getMe(tokenInfo);
    }

    @PostMapping("signup")
    public SignupResponse signup(@RequestBody SignupRequest signupRequest, @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        return customerService.signup(signupRequest, tokenInfo);
    }

    @GetMapping("{id}")
    public Customer getById(@PathVariable String id) {
        return customerService.getById(UUID.fromString(id));
    }
}
