package com.example.movie.dto;

import com.example.movie.domain.Member;
import com.example.movie.domain.Movie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreForm {
    private Movie movieId;
    private Member memberId;
    private float score;
}
