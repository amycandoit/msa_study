package com.example.pay.controller;

import com.example.pay.config.JwtService;
import com.example.pay.config.TokenInfo;
import com.example.pay.domain.request.PaymentRequest;
import com.example.pay.domain.response.PaymentResponse;
import com.example.pay.service.PaymentService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final PaymentService paymentService;
    private final JwtService jwtService;


    @GetMapping
    public List<PaymentResponse> getAll() {
        return paymentService.getAll();
    }
    @GetMapping("my")
    public List<PaymentResponse> getMy(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        return paymentService.getByCustomerId(tokenInfo);
    }

    @PostMapping
    public void save(@RequestBody PaymentRequest paymentRequest, @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        paymentService.savePayment(paymentRequest, tokenInfo);
    }
}
