package com.xzx.dto;

import com.xzx.entity.Scenarist;

import java.util.List;

public class ScenaristWithMovie {

    private Scenarist scenarist;
    private List<SimpleMovie> AMovies;
    private List<SimpleMovie> DMovies;
    private List<SimpleMovie> SMovies;

    public Scenarist getScenarist() {
        return scenarist;
    }

    public void setScenarist(Scenarist scenarist) {
        this.scenarist = scenarist;
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
