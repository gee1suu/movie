package com.example.movie.controller;

import com.example.movie.domain.Movie;
import com.example.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MovieService movieService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Movie> movieChart1 = movieService.loadMovieChart(0, 5);
        List<Movie> movieChart2 = movieService.loadMovieChart(5, 10);
        model.addAttribute("movieChart1", movieChart1);
        model.addAttribute("movieChart2", movieChart2);
        return "home";
    }
}
