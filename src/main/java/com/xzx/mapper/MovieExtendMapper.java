package com.xzx.mapper;

import com.xzx.entity.Movie;

import java.util.List;

public interface MovieExtendMapper {

    public List<Movie> getMovieByActorId(Integer actorId);
}
