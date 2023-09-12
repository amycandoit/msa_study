package com.example.user.domain.request;


import com.example.user.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoriteRequest {

    private Long userId;
    private Long storeId;

    public Favorite toEntity(Long userId, Long storeId) {

        return Favorite.builder()
                .user(User.builder().userId(userId).build())
                .store(Store.builder().id(storeId).build())
                .build();
    }
}
