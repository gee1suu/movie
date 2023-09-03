package com.example.movie.domain;

import com.example.movie.dto.ScoreForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "seq_score_id",
        sequenceName = "seq_score_id",
        allocationSize = 1)
@Getter
@Setter
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

    private float score; // 평점

    public static Score createScore(ScoreForm form) {
        Score score = new Score();
        score.setScore(form.getScore());
        score.setMemberId(form.getMemberId());
        score.setMovieId(form.getMovieId());

        return score;
    }

}
