package com.xzx.dto;

import com.xzx.dto.SimpleActor;
import com.xzx.entity.Movie;

import java.util.List;

public class MovieWithPeople {
    private Movie movie;
    private List<SimpleActor> actors;
    private List<SimpleDirector> directors;
    private List<SimpleScenarist> scenarists;

    public List<SimpleDirector> getDirectors() {
        return directors;
    }

    public void setDirectors(List<SimpleDirector> directors) {
        this.directors = directors;
    }

    public List<SimpleScenarist> getScenarists() {
        return scenarists;
    }

    public void setScenarists(List<SimpleScenarist> scenarists) {
        this.scenarists = scenarists;
    }

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
