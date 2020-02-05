package com.xzx.mapper;

import com.xzx.entity.Movie;

import java.util.List;

public interface MovieExtendMapper {

    public List<Movie> getMovieByActorId(Integer actorId);

    public List<Movie> getMovieByDirectorId(Integer directorId);

    public List<Movie> getMovieByScenaristId(Integer ScenaristId);

    public Integer insertMulti(List<Movie> movies);
}

