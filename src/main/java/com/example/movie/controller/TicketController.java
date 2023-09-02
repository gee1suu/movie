package com.example.movie.controller;

import com.example.movie.domain.Ticket;
import com.example.movie.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @RequestMapping("/myticket")
    public String myticket(Principal principal, Model model) {
        List<Ticket> ticketList = ticketService.loadMyticket(principal.getName());
        model.addAttribute("ticketList", ticketList);
        return "member/myticket";
    }

    @RequestMapping("/select-schedule")
    public String schedule() {
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
}
