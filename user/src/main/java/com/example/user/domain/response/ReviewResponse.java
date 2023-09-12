package com.example.user.domain.response;


import com.example.user.domain.dto.ReviewDto;
import com.example.user.domain.dto.UserDto;
import com.example.user.domain.entity.Review;
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
