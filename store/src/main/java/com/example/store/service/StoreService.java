package com.example.store.service;

import com.example.store.domain.request.StoreRequest;
import com.example.store.domain.response.StoreResponse;
import com.example.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public void storeSave(StoreRequest storeRequest, UUID ownerId) {
        storeRepository.save(storeRequest.toEntity(ownerId));
    }

    public List<StoreResponse> getAll() {
        return storeRepository.findAll().stream().map(StoreResponse::of).toList();
    }

    public List<StoreResponse> getByOwnerId(UUID ownerId) {
        return storeRepository.findByOwnerId(ownerId);
    }
    public Page<StoreResponse> getByLocation(String location, PageRequest pageRequest) {
        return storeRepository.findByLocation(location, pageRequest);
    }
}
