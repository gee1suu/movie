package com.example.movie.controller;

import com.example.movie.domain.Code;
import com.example.movie.domain.Movie;
import com.example.movie.dto.RegionDto;
import com.example.movie.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @RequestMapping("/cinemaList")
    public String cinemaList(int region, Model model) {
        List<Code> regionList = cinemaService.findRegions();
        RegionDto regionDto = cinemaService.findRegionDto(regionList.get(region - 1));
        List<Movie> movieList = cinemaService.findMovieByRegion(regionList.get(region - 1));
        model.addAttribute("regionList", regionList);
        model.addAttribute("regionDto", regionDto);
        model.addAttribute("movieList", movieList);
        return "/cinema/cinemaList";
    }
}
