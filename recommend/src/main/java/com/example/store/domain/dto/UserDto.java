package com.example.store.domain.dto;


import com.example.store.domain.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long userId;
    private String username;
    private String email;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}