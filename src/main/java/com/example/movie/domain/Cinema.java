package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Cinema {
    @Id
    @Column(name="cinema_id")
    private String id; // 코드

    @Column(name="cinema_name")
    private String name; // 이름

    @ManyToOne
    @JoinColumn(name="screen", referencedColumnName = "code_id")
    private Code screen; // 스크린

    @ManyToOne
    @JoinColumn(name="total_seats", referencedColumnName = "code_id")
    private Code totalSeats; // 총좌석수
}
