package com.example.store.domain.response;


import com.example.store.domain.dto.CommentDto;
import com.example.store.domain.dto.UserDto;
import com.example.store.domain.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse extends CommentDto {
    private UserDto user;
    private Long userId;

    public CommentResponse(Comment comment) {
        super(comment);
        user = new UserDto(comment.getUser());
        userId = comment.getUser().getUserId();
    }
}
