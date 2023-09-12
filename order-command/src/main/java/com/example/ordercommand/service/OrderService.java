package com.example.ordercommand.service;

import com.example.ordercommand.client.api.OrderQueryClient;
import com.example.ordercommand.domain.entity.Order;
import com.example.ordercommand.domain.request.OrderRequest;
import com.example.ordercommand.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderQueryClient orderQueryClient;
// 다른 곳에서 데이터를 받아온다
// 저장 -> h2(in memory)
// 이 서버 장점 : h2(서버가 죽음 = DB 죽음)
// 다중 서버를 계속 돌리면 저장 하는 DB가 늘어난다
// CQRS(c는 command, rud)
    public void saveOrder(OrderRequest orderRequest) {
        try {
            Order save = orderRepository.save(orderRequest.toEntity());
            //of 요청 보냄
            orderQueryClient.save(save);
            orderRepository.delete(save);
        } catch (Exception e) {

        }

        //of query save 성공을 하면 delete
        // 아니면 대기
        // 대기중인 데이터를 1분에 한번 select All(scheduler 사용)
    }
}
