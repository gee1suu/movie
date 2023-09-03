package com.example.movie.controller;

import com.example.movie.domain.Movie;
import com.example.movie.domain.Score;
import com.example.movie.domain.Ticket;
import com.example.movie.dto.ScoreForm;
import com.example.movie.service.MovieService;
import com.example.movie.service.ScoreService;
import com.example.movie.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final MovieService movieService;
    private final ScoreService scoreService;

    @RequestMapping("/myticket")
    public String myticket(Principal principal, Model model) {
        List<Ticket> ticketList = ticketService.loadMyticket(principal.getName());
        model.addAttribute("ticketList", ticketList);
        List<Score> scoreList = scoreService.loadMyscore(principal.getName());
        List<Long> movieList = new ArrayList<>();
        for(Score s : scoreList) {
            movieList.add(s.getMovieId().getId());
        }
        model.addAttribute("movieList", movieList);
        model.addAttribute("scoreForm", new ScoreForm());
        return "member/myticket";
    }

    @RequestMapping("/select-schedule")
    public String schedule(Model model) {
        List<Movie> movieList = movieService.loadMovies();
        model.addAttribute("movieList", movieList);
        return "/ticket/selectSchedule";
    }

    @RequestMapping("/select-seat")
    public String seat() {
        return "/ticket/selectSeat";
    }

    @RequestMapping("/select-payment")
    public String payment() {
        return "/ticket/selectPayment";
    }

    @RequestMapping("/ticket-result")
    public String result() {
        return "/ticket/ticketResult";
    }

    @PostMapping("/cancel-ticket")
    public String cancelTicket(Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/myticket";
    }
}
