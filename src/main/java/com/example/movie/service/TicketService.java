package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.domain.Ticket;
import com.example.movie.repository.MemberRepository;
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

    public List<Ticket> loadMyticket(String email) {
        Member findMember = memberRepository.findByEmail(email).get(0);
        return ticketRepository.findTicketById(findMember.getId());
    }
}
