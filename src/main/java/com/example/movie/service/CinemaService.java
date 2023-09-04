package com.example.movie.service;

import com.example.movie.domain.Cinema;
import com.example.movie.domain.Code;
import com.example.movie.domain.Movie;
import com.example.movie.domain.Schedule;
import com.example.movie.dto.CinemaDto;
import com.example.movie.dto.RegionDto;
import com.example.movie.dto.ScheduleDto;
import com.example.movie.repository.CinemaRepository;
import com.example.movie.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final TicketRepository ticketRepository;

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

    public List<CinemaDto> findCinemaByRegion(Code cinema) {
        List<Schedule> scheduleList = cinemaRepository.findScheduleByRegion(cinema);
        List<CinemaDto> cinemaDtos = new ArrayList<>();
        for(Schedule s : scheduleList) {
            CinemaDto cinemaDto = new CinemaDto();
            cinemaDto.setScreen(s.getScreen());
            cinemaDto.setName(s.getCinemaId().getName());
            cinemaDto.setMovieId(s.getMovieId().getId());
            cinemaDto.setTotalSeats(s.getCinemaId().getTotalSeats());
            cinemaDtos.add(cinemaDto);
        }
        return cinemaDtos.stream().distinct().collect(Collectors.toList());
    }

    public List<ScheduleDto> findScheduleByRegion(Code cinema) {
        List<Schedule> scheduleList = cinemaRepository.findScheduleByRegion(cinema);
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for(Schedule s : scheduleList) {
            ScheduleDto dto = new ScheduleDto();
            dto.setCinemaId(s.getCinemaId());
            dto.setId(s.getId());
            dto.setScreen(s.getScreen());
            dto.setOpenTime(s.getOpenTime());
            dto.setEndTime(s.getEndTime());
            dto.setMovieId(s.getMovieId());
            dto.setRemainSeats(Integer.parseInt(s.getCinemaId().getTotalSeats().getName()) - ticketRepository.findTicketByScheduleId(s.getId()).size());
            scheduleDtos.add(dto);
        }
        return scheduleDtos;
    }
}
