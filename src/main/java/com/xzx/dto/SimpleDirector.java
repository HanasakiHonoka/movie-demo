package com.xzx.dto;

public class SimpleDirector {
    private Integer id;

    public SimpleDirector() {
        super();
    }

    private String name;

    public SimpleDirector(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
