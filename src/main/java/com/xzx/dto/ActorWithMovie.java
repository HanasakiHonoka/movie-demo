package com.xzx.dto;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Actor;

import java.util.List;

public class ActorWithMovie {

    private Actor actor;
    private List<SimpleMovie> movies;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<SimpleMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<SimpleMovie> movies) {
        this.movies = movies;
    }
}
