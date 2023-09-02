package com.example.movie.repository;

import com.example.movie.domain.Score;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ScoreRepository {
    @PersistenceContext
    EntityManager em;

    public List<Score> findScoreByMemberId(Long id) {
        return em.createQuery(
                        "select s from Score s where s.memberId.id = :id ",
                        Score.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Score> findScoreById(Long id) {
        return em.createQuery(
                        "select s from Score s where s.id = :id ",
                        Score.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void delete(Score score) {
        em.remove(score);
    }
}
