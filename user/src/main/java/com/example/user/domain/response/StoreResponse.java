package com.example.user.domain.response;


import com.example.user.domain.dto.StoreDto;
import com.example.user.domain.dto.UserDto;
import com.example.user.domain.entity.Favorite;
import com.example.user.domain.entity.Recommend;
import com.example.user.domain.entity.Store;
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
