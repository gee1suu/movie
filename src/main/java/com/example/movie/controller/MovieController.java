package com.example.movie.controller;

import com.example.movie.domain.Movie;
import com.example.movie.dto.FutureMovieDto;
import com.example.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @RequestMapping("/movieList-1")
    public String movieList_1() {
        return "movie/movieList_1";
    }

    @RequestMapping("/movieList-2")
    public String movieList_2(Model model) {
        List<FutureMovieDto> movieList = movieService.loadFutureMovies();
        model.addAttribute("movieList", movieList);
        return "movie/movieList_2";
    }

    @RequestMapping("/movieDetail")
    public String movieDetail(@RequestParam Long id, Model model) {
        Movie movie = movieService.findMovieById(id);
        model.addAttribute("movie", movie);
        return "movie/movieDetail";
    }
}
