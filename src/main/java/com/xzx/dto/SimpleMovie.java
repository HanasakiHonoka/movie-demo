package com.xzx.dto;

public class SimpleMovie {

    private Integer id;
    private String title;

    public Integer getId() {
        return id;
    }

    public SimpleMovie() {
        super();
    }

    public SimpleMovie(Integer id, String title) {
        this.id = id;
        this.title = title;
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
