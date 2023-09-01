package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class Schedule {
    @Id
    @Column(name = "schedule_id")
    private Long id; // 코드

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movieId; // 영화

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinema_id")
    private Cinema cinemaId; // 상영관

    private Timestamp openTime; // 시작시간
    private Timestamp endTime; // 종료시간
}
