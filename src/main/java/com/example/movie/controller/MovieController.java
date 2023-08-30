package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
    @RequestMapping("/movieList")
    public String movieList() {
        return "movie/movieList";
    }

    @RequestMapping("/movieDetail")
    public String movieDetail(@RequestParam int id, Model model) {
        model.addAttribute("id", id);
        return "movie/movieDetail";
    }
}
