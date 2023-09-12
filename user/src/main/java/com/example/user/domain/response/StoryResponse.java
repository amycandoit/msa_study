package com.example.user.domain.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoryResponse {

    private Long userId;
//    private String username;
    private Long count;
    private Long storeCount;
}
