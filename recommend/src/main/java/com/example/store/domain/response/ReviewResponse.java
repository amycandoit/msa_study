package com.example.store.domain.response;


import com.example.store.domain.dto.ReviewDto;
import com.example.store.domain.dto.UserDto;
import com.example.store.domain.entity.Review;
import lombok.Getter;

@Getter
public class ReviewResponse extends ReviewDto {
    private UserDto user;
    private Long userId;

    public ReviewResponse(Review review) {
        super(review);
        user = new UserDto(review.getUser());
        userId = review.getUser().getUserId();
    }
}
