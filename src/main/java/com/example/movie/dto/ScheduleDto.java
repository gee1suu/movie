package com.example.movie.dto;

import com.example.movie.domain.Cinema;
import com.example.movie.domain.Code;
import com.example.movie.domain.Movie;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ScheduleDto {
    private Long id; // 코드
    private Movie movieId; // 영화
    private Cinema cinemaId;
    private Code screen;
    private Timestamp openTime;
    private Timestamp endTime;
    private int remainSeats; // 남은 좌석수(계산 필요)
}
