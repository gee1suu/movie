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

    public List<Ticket> findTicketById(Long id) {
        return em.createQuery(
                        "select t from Ticket t where t.memberId.id = :id ",
                        Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }
}
