package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.domain.Movie;
import com.example.movie.domain.Score;
import com.example.movie.repository.MemberRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScoreService {
    private final MemberRepository memberRepository;
    private final ScoreRepository scoreRepository;
    private final MovieRepository movieRepository;

    public List<Score> loadMyscore(String email) {
        Member findMember = memberRepository.findByEmail(email).get(0);
        return scoreRepository.findScoreByMemberId(findMember.getId());
    }

    public void modifyScore(float score, Long id) {
        Score findScore = scoreRepository.findScoreById(id).get(0);
        findScore.setScore(score);
        getAverageScore(findScore.getMovieId().getId());
    }

    public void deleteScore(Long id) {
        Score findScore = scoreRepository.findScoreById(id).get(0);
        scoreRepository.delete(findScore);
        getAverageScore(findScore.getMovieId().getId());
    }

    public void addScore(Score score) {
        scoreRepository.save(score);
        getAverageScore(score.getMovieId().getId());
    }

    public void getAverageScore(Long movieId) {
        Movie findMovie = movieRepository.findMovieById(movieId).get(0);
        List<Score> scoreList = scoreRepository.findScoreByMovieId(movieId);
        float avgGrade = 0.0f;
        if(scoreList.isEmpty()) {
            findMovie.setAvgGrade(avgGrade);
        } else {
            for(Score s : scoreList) {
                avgGrade += s.getScore();
            }

            findMovie.setAvgGrade(avgGrade / scoreList.size());
        }
    }
}
