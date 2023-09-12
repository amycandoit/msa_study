package com.example.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favorite_id;


    @JsonIgnoreProperties("favorites")
    @ManyToOne
    private User user;

    @JsonIgnoreProperties("favorites")
    @ManyToOne
    private Store store;

}