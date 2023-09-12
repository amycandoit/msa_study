package com.example.store.domain.response;

import com.example.store.domain.dto.StoreDto;
import com.example.store.domain.dto.UserDto;
import com.example.store.domain.entity.Recommend;
import lombok.Getter;

@Getter
public class RecommendResponse {

    private UserDto userDto;
    private StoreDto storeDto;

    public RecommendResponse(Recommend recommend) {

        userDto = new UserDto(recommend.getUser());
        storeDto = new StoreDto(recommend.getStore());
    }
}
