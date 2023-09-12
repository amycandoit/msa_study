package com.example.pay.client.api;

import com.example.pay.domain.dto.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("MENU-SERVICE")
public interface MenuClient {
    @GetMapping("/api/v1/menu/ids")
    List<Menu> getAllByIds(@RequestParam(name = "ids") List<Long> ids);
}
