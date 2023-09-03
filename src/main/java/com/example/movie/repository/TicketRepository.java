package com.example.movie.repository;

import com.example.movie.domain.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TicketRepository {
    @PersistenceContext
    EntityManager em;

    public List<Ticket> findTicketByMemberId(Long id) {
        return em.createQuery(
                        "select t from Ticket t where t.memberId.id = :id " +
                                "order by t.ticketDate ",
                        Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Ticket> findTicketById(Long id) {
        return em.createQuery(
                        "select t from Ticket t where t.id = :id ",
                        Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void delete(Ticket ticket) {
        em.remove(ticket);
    }
}