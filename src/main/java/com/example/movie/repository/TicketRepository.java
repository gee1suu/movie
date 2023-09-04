package com.example.movie.repository;

import com.example.movie.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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

    public int findCurrentTickets() {
        Date now = new Date();
        return em.createQuery(
                        "select t from Ticket t where t.scheduleId.movieId.endDate > :now ",
                        Ticket.class)
                .setParameter("now", now)
                .getResultList()
                .size();
    }

    public int findTicketByMovieId(Long movieId) {
        return em.createQuery(
                        "select t from Ticket t where t.scheduleId.movieId.id = :id ",
                        Ticket.class)
                .setParameter("id", movieId)
                .getResultList()
                .size();
    }

    public List<Ticket> findTicketByScheduleId(Long id) {
        return em.createQuery(
                        "select t from Ticket t where t.scheduleId.id = :id ",
                        Ticket.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<String> findTotalSeatsByScheduleId(Long id) {
        return em.createQuery(
                        "select s.cinemaId.totalSeats.name from Schedule s where s.id = :id ",
                        String.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<String> findScreenByScheduleId(Long scheduleId) {
        return em.createQuery(
                        "select s.screen.id from Schedule s where s.id = :id ",
                        String.class)
                .setParameter("id", scheduleId)
                .getResultList();
    }

    public List<Price> findPriceByScreen(String screenId) {
        return em.createQuery(
                        "select p from Price p where p.screen.id = :id ",
                        Price.class)
                .setParameter("id", screenId)
                .getResultList();
    }

    public List<Price> findPriceByScreenAndAudience(String screenId, String audience) {
        return em.createQuery(
                        "select p from Price p where p.screen.id = :scr and p.audience.id = :aud",
                        Price.class)
                .setParameter("scr", screenId)
                .setParameter("aud", audience)
                .getResultList();
    }

    public List<Movie> findMovieByScheduleId(Long id) {
        return em.createQuery(
                        "select t.scheduleId.movieId from Ticket t where t.scheduleId.id = :id ",
                        Movie.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void save(Ticket ticket) {
        em.persist(ticket);
    }

    public List<Code> findCodeByCodeId(String paymentMethod) {
        return em.createQuery(
                        "select c from Code c where c.id = :id ",
                        Code.class)
                .setParameter("id", paymentMethod)
                .getResultList();
    }

    public List<Schedule> findScheduleByScheduleId(Long scheduleId) {
        return em.createQuery(
                        "select s from Schedule s where s.id = :id ",
                        Schedule.class)
                .setParameter("id", scheduleId)
                .getResultList();
    }
}
