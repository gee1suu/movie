package com.example.movie.dto;

import com.example.movie.domain.Code;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CinemaDto {
    private Code screen;
    private String name; // 이름
    private Code totalSeats; // 총좌석수
    private Long movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaDto cinemaDto = (CinemaDto) o;
        return Objects.equals(screen, cinemaDto.screen) && Objects.equals(name, cinemaDto.name) && Objects.equals(totalSeats, cinemaDto.totalSeats) && Objects.equals(movieId, cinemaDto.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screen, name, totalSeats, movieId);
    }
}
