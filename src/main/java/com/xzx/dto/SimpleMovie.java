package com.xzx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SimpleMovie {

    private Integer id;
    private String title;
    private Float boxoffice;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releaseTime;

    public Integer getId() {
        return id;
    }

    public SimpleMovie() {
        super();
    }

    public SimpleMovie(Integer id, String title, Float boxoffice, Date releaseTime) {
        this.id = id;
        this.title = title;
        this.boxoffice = boxoffice;
        this.releaseTime = releaseTime;
    }

    public Float getBoxoffice() {
        return boxoffice;
    }

    public void setBoxoffice(Float boxoffice) {
        this.boxoffice = boxoffice;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
