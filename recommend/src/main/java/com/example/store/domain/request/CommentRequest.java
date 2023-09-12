package com.example.store.domain.request;


import com.example.store.domain.entity.Comment;
import com.example.store.domain.entity.Review;
import com.example.store.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String content;

    public Comment toEntity(Long userId, Long reviewSeq) {
        return Comment.builder()
                .content(content)
                .user(User.builder().userId(userId).build())
                .review(Review.builder().reviewSeq(reviewSeq).build())
                .build();
    }
}
