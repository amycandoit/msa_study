package com.example.user.domain.response;


import com.example.user.domain.dto.CommentDto;
import com.example.user.domain.dto.UserDto;
import com.example.user.domain.entity.Comment;
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
