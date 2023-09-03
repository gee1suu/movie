package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Price {
    @Id
    @Column(name = "price_id")
    private Long id; // 코드

    @ManyToOne
    @JoinColumn(name = "screen", referencedColumnName = "code_id")
    private Code screen; // 스크린

    @ManyToOne
    @JoinColumn(name = "audience", referencedColumnName = "code_id")
    private Code audience; // 가격

    @Column(name = "price_name")
    private String name;

    private int price; // 가격
}
