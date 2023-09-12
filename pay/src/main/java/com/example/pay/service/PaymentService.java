package com.example.pay.service;

import com.example.pay.client.api.CustomerClient;
import com.example.pay.client.api.MenuClient;
import com.example.pay.client.api.OrderCommandClient;
import com.example.pay.config.TokenInfo;
import com.example.pay.domain.dto.Customer;
import com.example.pay.domain.dto.Menu;
import com.example.pay.domain.entity.Payment;
import com.example.pay.domain.request.OrderRequest;
import com.example.pay.domain.request.PaymentRequest;
import com.example.pay.domain.response.PaymentResponse;
import com.example.pay.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CustomerClient customerClient;
    private final MenuClient menuClient;
    private final OrderCommandClient orderCommandClient;

    public void savePayment(PaymentRequest paymentRequest, TokenInfo tokenInfo) {
        Payment save = paymentRepository.save(paymentRequest.toEntity(tokenInfo));
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setStoreId(save.getStoreId());
        orderRequest.setCustomerId(save.getCustomerId().toString());
        orderRequest.setPrice(save.getPrice());
        orderCommandClient.save(orderRequest);
    }

    public List<PaymentResponse> getAll() {
        List<Payment> all = paymentRepository.findAll();
        return getPaymentResponses(all);
    }

    private List<PaymentResponse> getPaymentResponses(List<Payment> all) {
        Map<UUID, Customer> customerMap = new HashMap<>();
        return all.stream().map(payment -> {
            Customer customer = customerClient.getById(payment.getCustomerId().toString());
            List<Menu> menus = menuClient.getAllByIds(Arrays.stream(payment.getMenuIds().split(","))
                    .map(Long::parseLong).toList());
            return new PaymentResponse(payment, menus, customer);
        }).toList();
    }

    public List<PaymentResponse> getByCustomerId(TokenInfo tokenInfo) {
        List<Payment> all = paymentRepository.findByCustomerId(tokenInfo.getId());
        return getPaymentResponses(all);
    }


}
