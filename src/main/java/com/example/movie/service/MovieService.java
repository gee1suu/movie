package com.example.movie.service;

import com.example.movie.domain.Movie;
import com.example.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> loadMovieChart(int start, int end) {
        List<Movie> movieList = movieRepository.loadMovieList();
        return movieList.subList(start, end);
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findMovieById(id).get(0);
    }
}
