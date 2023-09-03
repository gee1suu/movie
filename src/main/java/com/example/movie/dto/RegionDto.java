package com.example.movie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDto {
    private String regionId; // 코드
    private String regionName; // 이름
    private int numberOfCinema; // 총 상영관 수
    private int totalSeats; // 총 좌석 수
}
