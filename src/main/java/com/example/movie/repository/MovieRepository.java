package com.example.movie.repository;

import com.example.movie.domain.Member;
import com.example.movie.domain.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class MovieRepository {
    @PersistenceContext
    EntityManager em;

    public List<Movie> loadMovieList() {
        Date now = new Date();
        return em.createQuery(
                        "select m from Movie m where m.openDate < :now" +
                                " order by m.bookingRate desc ",
                        Movie.class)
                .setParameter("now", now)
                .getResultList();
    }

    public List<Movie> findMovieById(Long id) {
        return em.createQuery(
                        "select m from Movie m where m.id = :id ",
                        Movie.class)
                .setParameter("id", id)
                .getResultList();
    }
}
