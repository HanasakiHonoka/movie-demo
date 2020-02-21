package com.xzx.entity;

public class NeoMovie {

    private String mid;
    private String name;

    public String getMid() {
        return mid;
    }

    public NeoMovie(String mid, String name) {
        this.mid = mid;
        this.name = name;
    }

    public NeoMovie() {
        super();
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
