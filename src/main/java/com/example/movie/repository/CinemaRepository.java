package com.example.movie.repository;

import com.example.movie.domain.Cinema;
import com.example.movie.domain.Code;
import com.example.movie.domain.Movie;
import com.example.movie.domain.Schedule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class CinemaRepository {
    @PersistenceContext
    EntityManager em;

    public List<Code> findRegions() {
        return em.createQuery(
                        "select c.regionId from Cinema c order by c.regionId.id ",
                        Code.class)
                .getResultList();
    }

    public List<Cinema> findCinemas() {
        return em.createQuery(
                        "select c from Cinema c ",
                        Cinema.class)
                .getResultList();
    }

    public List<Movie> findMovieByRegion(Code cinema) {
        Date now = new Date();
        return em.createQuery(
                        "select s.movieId from Schedule s where s.cinemaId.regionId = :id " +
                                "and s.openTime > :now ",
                        Movie.class)
                .setParameter("id", cinema)
                .setParameter("now", now)
                .getResultList();
    }

    public List<Schedule> findScheduleByRegion(Code cinema) {
        Date now = new Date();
        return em.createQuery(
                        "select s from Schedule s where s.cinemaId.regionId = :id " +
                                "and s.openTime > :now order by s.openTime, s.cinemaId.id ",
                        Schedule.class)
                .setParameter("id", cinema)
                .setParameter("now", now)
                .getResultList();
    }
}
