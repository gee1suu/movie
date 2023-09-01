package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "seq_score_id",
        sequenceName = "seq_score_id",
        allocationSize = 1)
@Getter
public class Score {
    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_score_id")
    @Column(name = "score_id")
    private Long id; // 코드

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movieId; // 영화코드

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member memberId; // 고객코드

    private int score; // 평점
}
