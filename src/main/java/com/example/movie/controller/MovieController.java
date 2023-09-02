package com.example.movie.controller;

import com.example.movie.domain.Movie;
import com.example.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @RequestMapping("/movieList")
    public String movieList() {
        return "movie/movieList";
    }

    @RequestMapping("/movieDetail")
    public String movieDetail(@RequestParam Long id, Model model) {
        Movie movie = movieService.findMovieById(id);
        model.addAttribute("movie", movie);
        return "movie/movieDetail";
    }
}
