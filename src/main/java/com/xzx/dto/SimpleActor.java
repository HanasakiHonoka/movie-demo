package com.xzx.dto;

public class SimpleActor {
    private Integer id;
    private String name;

    public SimpleActor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleActor() {
        super();
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
