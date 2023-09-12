package com.example.store.domain.response;


import com.example.store.domain.dto.StoreDto;
import com.example.store.domain.dto.UserDto;
import com.example.store.domain.entity.Favorite;
import com.example.store.domain.entity.Recommend;
import com.example.store.domain.entity.Store;
import lombok.Getter;

import java.util.List;

@Getter
public class StoreResponse extends StoreDto {
    private List<UserDto> users;
    private List<UserDto> favorites;

    public StoreResponse(Store store) {
        super(store);
        users = store.getUsers().stream().map(Recommend::getUser).map(UserDto::new).toList();
        favorites = store.getFavorites().stream().map(Favorite::getUser).map(UserDto::new).toList();
    }
}
