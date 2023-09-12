package com.example.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recommend")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonIgnoreProperties("recommends")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties("users")
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
