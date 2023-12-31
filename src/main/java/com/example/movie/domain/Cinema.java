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
    @JoinColumn(name="total_seats", referencedColumnName = "code_id")
    private Code totalSeats; // 총좌석수

    @ManyToOne
    @JoinColumn(name="region_id", referencedColumnName = "code_id")
    private Code regionId; // 지역
}
