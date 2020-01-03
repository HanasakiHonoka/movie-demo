package com.xzx.dto;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Actor;

import java.util.List;

public class ActorWithMovie {

    private Actor actor;
    private List<SimpleMovie> AMovies;
    private List<SimpleMovie> DMovies;
    private List<SimpleMovie> SMovies;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<SimpleMovie> getAMovies() {
        return AMovies;
    }

    public void setAMovies(List<SimpleMovie> AMovies) {
        this.AMovies = AMovies;
    }

    public List<SimpleMovie> getDMovies() {
        return DMovies;
    }

    public void setDMovies(List<SimpleMovie> DMovies) {
        this.DMovies = DMovies;
    }

    public List<SimpleMovie> getSMovies() {
        return SMovies;
    }

    public void setSMovies(List<SimpleMovie> SMovies) {
        this.SMovies = SMovies;
    }
}
