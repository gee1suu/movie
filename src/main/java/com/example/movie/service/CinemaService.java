package com.example.movie.service;

import com.example.movie.domain.Cinema;
import com.example.movie.domain.Code;
import com.example.movie.domain.Movie;
import com.example.movie.dto.RegionDto;
import com.example.movie.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;

    public List<Code> findRegions() {
        return cinemaRepository.findRegions().stream().distinct().collect(Collectors.toList());
    }

    public RegionDto findRegionDto(Code region) {
        List<Cinema> cinemaList = cinemaRepository.findCinemas();
        RegionDto regionDto = new RegionDto();
        int numberOfCinema = 0;
        int totalSeats = 0;

        regionDto.setRegionId(region.getId());
        regionDto.setRegionName(region.getName());

        for(Cinema c : cinemaList) {
            if(c.getRegionId().getId().equals(region.getId())) {
                numberOfCinema++;
                totalSeats += Integer.parseInt(c.getTotalSeats().getName());
            }
        }

        regionDto.setNumberOfCinema(numberOfCinema);
        regionDto.setTotalSeats(totalSeats);

        return regionDto;
    }

    public List<Movie> findMovieByRegion(Code cinema) {
        return cinemaRepository.findMovieByRegion(cinema).stream().distinct().collect(Collectors.toList());
    }

}
