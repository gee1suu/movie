package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Movie {
    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id; // 코드

    @Column(name = "movie_name")
    private String name; // 이름

    @ManyToOne
    @JoinColumn(name = "genre", referencedColumnName = "code_id")
    private Code genre; // 장르

    @ManyToOne
    @JoinColumn(name = "movie_rating", referencedColumnName = "code_id")
    private Code movieRating; // 관람등급

    private int runningTime; // 상영시간
    private String actors; // 배우
    private Date openDate; // 개봉일
    private Date endDate; // 종영일
    private String summary; // 설명
    private String company; // 배급사
    private String director; // 감독
    private float avgGrade; // 평균평점
    private float bookingRate; // 예매율
}
