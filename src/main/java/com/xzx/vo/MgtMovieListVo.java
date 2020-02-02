package com.xzx.vo;

import com.xzx.dto.MovieWithPeople;
import com.xzx.entity.Movie;

import java.util.List;

public class MgtMovieListVo {

    private List<MovieWithPeople> movies;

    public List<MovieWithPeople> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieWithPeople> movies) {
        this.movies = movies;
    }

    public MgtMovieListVo() {
        super();
    }

    public MgtMovieListVo(List<MovieWithPeople> movies) {
        this.movies = movies;
    }
}
