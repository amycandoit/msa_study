package com.example.user.domain.response;

import com.example.user.domain.dto.StoreDto;
import com.example.user.domain.dto.UserDto;
import com.example.user.domain.entity.Recommend;
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
