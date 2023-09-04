package com.example.movie.service;

import com.example.movie.domain.*;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {
    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;

    public List<Ticket> loadMyticket(String email) {
        Member findMember = memberRepository.findByEmail(email).get(0);
        return ticketRepository.findTicketByMemberId(findMember.getId());
    }

    public void deleteTicket(Long id) {
        Ticket findTicket = ticketRepository.findTicketById(id).get(0);
        ticketRepository.delete(findTicket);
        getBookingRate(findTicket.getScheduleId().getMovieId().getId());
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
        getBookingRate(ticketRepository.findMovieByScheduleId(ticket.getScheduleId().getId()).get(0).getId());
    }

    private void getBookingRate(Long movieId) {
        Movie findMovie = movieRepository.findMovieById(movieId).get(0);
        float bookingRate = 0.0f;

        int allTickets = ticketRepository.findCurrentTickets(); // 현재상영작을 예매한 모든 티켓수
        if(allTickets != 0) {
            int movieTickets = ticketRepository.findTicketByMovieId(movieId); // 해당 영화를 예매한 모든 티켓수
            if(movieTickets != 0) {
                bookingRate = ((float) movieTickets / allTickets) * 100; // 계산
            }
        }

        findMovie.setBookingRate(bookingRate);
    }

    public List<Ticket> findTicketByScheduleId(Long id) {
        return ticketRepository.findTicketByScheduleId(id);
    }

    public int findTotalSeatsByScheduleId(Long id) {
        return Integer.parseInt(ticketRepository.findTotalSeatsByScheduleId(id).get(0));
    }

    public String findScreenByScheduleId(Long scheduleId) {
        return ticketRepository.findScreenByScheduleId(scheduleId).get(0);
    }

    public List<Price> findPriceByScreen(String screenId) {
        return ticketRepository.findPriceByScreen(screenId);
    }

    public Price findPriceByScheduleIdAndAudience(Long scheduleId, String audience) {
        System.out.println("screen: " + ticketRepository.findScreenByScheduleId(scheduleId).get(0));
        System.out.println("audience: " + audience);
        return ticketRepository.findPriceByScreenAndAudience
                (ticketRepository.findScreenByScheduleId(scheduleId).get(0), audience)
                .get(0);
    }

    public Code findCodeByCodeId(String paymentMethod) {
        return ticketRepository.findCodeByCodeId(paymentMethod).get(0);
    }

    public Schedule findScheduleByScheduleId(Long scheduleId) {
        return ticketRepository.findScheduleByScheduleId(scheduleId).get(0);
    }
}
