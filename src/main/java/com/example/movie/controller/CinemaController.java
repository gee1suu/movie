package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CinemaController {
    @RequestMapping("/cinemaList")
    public String movieList() {
        return "/cinema/cinemaList";
    }
}
