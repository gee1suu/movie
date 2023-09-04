package com.example.movie.controller;

import com.example.movie.domain.*;
import com.example.movie.dto.MessageDto;
import com.example.movie.dto.ScoreForm;
import com.example.movie.service.MemberService;
import com.example.movie.service.MovieService;
import com.example.movie.service.ScoreService;
import com.example.movie.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final MovieService movieService;
    private final ScoreService scoreService;
    private final MemberService memberService;

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
        if(isAuthenticated()) {
            List<Movie> movieList = movieService.loadMovies();
            model.addAttribute("movieList", movieList);
            return "/ticket/selectSchedule";
        } else {
            MessageDto message = new MessageDto("로그인이 필요한 기능입니다.", "/", RequestMethod.GET, null);
            return showMessageAndRedirect(message, model);
        }
    }

    @RequestMapping("/select-seat")
    public String seat(Long id, Model model) {
        if(isAuthenticated()) {
            List<Ticket> ticketList = ticketService.findTicketByScheduleId(id);
            int totalSeats = ticketService.findTotalSeatsByScheduleId(id);
            model.addAttribute("ticketList", ticketList);
            model.addAttribute("totalSeats", totalSeats);
            return "/ticket/selectSeat";
        } else {
            MessageDto message = new MessageDto("로그인이 필요한 기능입니다.", "/", RequestMethod.GET, null);
            return showMessageAndRedirect(message, model);
        }
    }

    @RequestMapping("/select-payment")
    public String payment(int adu, int ado, int sen, int dis, String seat, Long scheduleId, Model model) {
        // 성인, 청소년, 시니어, 장애인, 좌석, 스케줄 아이디

        String screenId = ticketService.findScreenByScheduleId(scheduleId); // 스케줄 아이디에서 screen 가져오기
        List<Price> prices = ticketService.findPriceByScreen(screenId); // screen과 관객종류 조합해서 price 가져오기
        String audienceList = adu + "," + ado + "," + sen + "," + dis;

        model.addAttribute("prices", prices);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("seat", seat);
        model.addAttribute("audienceList", audienceList);

        return "/ticket/selectPayment";
    }

    @RequestMapping("/ticket-result")
    public String result(String paymentMethod, String seat, Long scheduleId, String audienceList, Principal principal) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String[] seatList = seat.split(","); // [3_4, 4_5, 5_6]
        String[] a = audienceList.split(","); // [2,1,0,0]
        int[] audience = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            audience[i] = Integer.parseInt(a[i]);
        }
        String[] audienceType = { "ADU", "ADO", "SEN", "DIS" };
        List<String> result = new ArrayList<>();

        for(int i = 0; i < audience.length; i++) {
            for(int j = 0; j < audience[i]; j++) {
                result.add(audienceType[i]);
            }
        }

        for(int i = 0; i < seatList.length; i++) {
            String[] rc = seatList[i].split("_"); // [row, col]
            Ticket ticket = new Ticket();
            ticket.setTicketDate(now);
            ticket.setMemberId(memberService.findIdByEmail(principal.getName()));
            ticket.setPriceId(ticketService.findPriceByScheduleIdAndAudience(scheduleId, result.get(i)));
            ticket.setPaymentMethod(ticketService.findCodeByCodeId(paymentMethod));
            ticket.setScheduleId(ticketService.findScheduleByScheduleId(scheduleId));
            ticket.setSeatRow(Integer.parseInt(rc[0]));
            ticket.setSeatCol(Integer.parseInt(rc[1]));
            ticketService.addTicket(ticket);
        }
        return "redirect:/myticket";
    }

    @PostMapping("/cancel-ticket")
    public String cancelTicket(Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/myticket";
    }

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "/messageRedirect";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
