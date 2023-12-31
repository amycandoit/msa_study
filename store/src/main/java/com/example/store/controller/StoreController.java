package com.example.store.controller;

import com.example.store.config.JwtService;
import com.example.store.config.TokenInfo;
import com.example.store.domain.request.StoreRequest;
import com.example.store.domain.response.StoreResponse;
import com.example.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {
    private final StoreService storeService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postStore(@RequestBody StoreRequest storeRequest, @RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        storeService.storeSave(storeRequest, tokenInfo.getId());
    }

    @GetMapping("/owner")
    public List<StoreResponse> getByOwnerId(@RequestHeader("Authorization") String token) {
        TokenInfo tokenInfo = jwtService.parseToken(token.replace("Bearer ", ""));
        return storeService.getByOwnerId(tokenInfo.getId());
    }

    @GetMapping
    public Page<StoreResponse> getByLocation(
            @RequestParam(required = true, name = "location") String location,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size
    ) {
        return storeService.getByLocation(location, PageRequest.of(page, size));
    }

    @GetMapping("test")
    public String test() {
        return "service-hello";
    }
}
