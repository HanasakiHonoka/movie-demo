package com.xzx.dto;

import com.xzx.entity.Director;
import com.xzx.entity.Movie;

import java.util.List;

public class DirectorWithMovie {

    private Director director;
    private List<SimpleMovie> AMovies;
    private List<SimpleMovie> DMovies;
    private List<SimpleMovie> SMovies;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
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
