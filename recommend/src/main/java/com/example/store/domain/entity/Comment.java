package com.example.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentSeq;
    @Column(columnDefinition = "TEXT")
    private String content; //댓글 내용

    @ManyToOne
    @JsonIgnoreProperties("user")
    @JoinColumn(name = "userId")
    private User user;

    @JsonIgnoreProperties("comments")
    @ManyToOne
    @JoinColumn(name = "review_seq")
    private Review review;

    public void update(String content) {
        this.content = content;
    }
}
