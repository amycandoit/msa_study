package com.example.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewSeq;
    @Column(columnDefinition = "TEXT")
    private String content; //리뷰  내용

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    

    public void update(String content) {
        this.content = content;
    }

    @JsonIgnoreProperties("review")
    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @JsonIgnoreProperties("reviews")
    @ManyToOne
    private Store store;
}
