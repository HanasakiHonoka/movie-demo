package com.xzx.dto;


public class SimpleScenarist {
    private Integer id;
    private String name;

    public SimpleScenarist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleScenarist() {
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
