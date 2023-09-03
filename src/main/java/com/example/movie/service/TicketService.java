package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.domain.Movie;
import com.example.movie.domain.Ticket;
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

    public void addTicket() {
        // getBookingRate();
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
}
