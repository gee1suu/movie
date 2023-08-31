package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {
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
