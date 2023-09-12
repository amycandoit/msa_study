package com.example.store.domain.request;

import com.example.store.domain.entity.Owner;
import com.example.store.domain.entity.Store;

import java.util.UUID;

public record StoreRequest(String location, String name, String img) {

    public Store toEntity(UUID ownerId) {
        return Store.builder()
                .location(location)
                .name(name)
                .img(img)
                .owner(Owner.builder().id(ownerId).build())
                .build();
    }
}
