package com.example.movie.service;

import com.example.movie.domain.Member;
import com.example.movie.domain.Score;
import com.example.movie.repository.MemberRepository;
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

    public List<Score> loadMyscore(String email) {
        Member findMember = memberRepository.findByEmail(email).get(0);
        return scoreRepository.findScoreByMemberId(findMember.getId());
    }

    public void modifyScore(float score, Long id) {
        List<Score> findScore = scoreRepository.findScoreById(id);
        findScore.get(0).setScore(score);
    }

    public void deleteScore(Long id) {
        Score findScore = scoreRepository.findScoreById(id).get(0);
        scoreRepository.delete(findScore);
    }
}
