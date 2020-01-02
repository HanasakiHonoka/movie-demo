package com.xzx.dto;

import com.xzx.dto.SimpleActor;
import com.xzx.entity.Movie;

import java.util.List;

public class MovieWithActor {
    private Movie movie;
    private List<SimpleActor> actors;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<SimpleActor> getActors() {
        return actors;
    }

    public void setActors(List<SimpleActor> actors) {
        this.actors = actors;
    }
}
