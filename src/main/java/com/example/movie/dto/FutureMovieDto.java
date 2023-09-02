package com.example.movie.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FutureMovieDto {
    private Long id; // 코드
    private String name; // 이름
    private Date openDate; // 개봉일
    private String poster; // 포스터
    private int dDay; // 디데이

    public FutureMovieDto(Long id, String name, Date openDate, String poster) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.poster = poster;
    }
}
