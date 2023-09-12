package com.example.user.domain.dto;


import com.example.user.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDto {
    private Long reviewSeq;
    private String content;

    public ReviewDto(Review review) {
        this.reviewSeq = review.getReviewSeq();
        this.content = review.getContent();
    }
}
