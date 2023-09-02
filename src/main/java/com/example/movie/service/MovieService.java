package com.example.movie.service;

import com.example.movie.domain.Movie;
import com.example.movie.dto.FutureMovieDto;
import com.example.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    public List<FutureMovieDto> loadFutureMovies() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long nowTime = now.getTime();

        List<Movie> movieList = movieRepository.loadFutureMovies();
        List<FutureMovieDto> futureMovieDtos = new ArrayList<>();
        for(Movie m : movieList) {
            FutureMovieDto futureMovieDto = new FutureMovieDto(m.getId(), m.getName(), m.getOpenDate(), m.getPoster());
            long movieTime = m.getOpenDate().getTime();
            futureMovieDto.setDDay((int)((movieTime - nowTime) / (24*60*60*1000) + 1));
            futureMovieDtos.add(futureMovieDto);
        }
        return futureMovieDtos;
    }

    public List<Movie> loadCurrentMoviesOrderByScore() {
        return movieRepository.loadCurrentMoviesOrderByScore();
    }

    public List<Movie> loadCurrentMoviesOrderByBooking() {
        return movieRepository.loadCurrentMoviesOrderByBooking();
    }
}
