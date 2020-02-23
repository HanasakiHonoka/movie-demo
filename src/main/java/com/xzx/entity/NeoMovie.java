package com.xzx.entity;

public class NeoMovie {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public NeoMovie(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public NeoMovie() {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
