package com.xzx.vo;

import com.xzx.dto.DirectorWithMovie;

import java.util.List;

public class MgtDirectorListVo {

    private List<DirectorWithMovie> directors;

    public List<DirectorWithMovie> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorWithMovie> directors) {
        this.directors = directors;
    }

    public MgtDirectorListVo() {
    }

    public MgtDirectorListVo(List<DirectorWithMovie> directors) {
        this.directors = directors;
    }
}
