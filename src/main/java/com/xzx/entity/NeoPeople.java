package com.xzx.entity;

public class NeoPeople {

    private String uid;
    private String name;

    @Override
    public String toString() {
        return "NeoPeople{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public NeoPeople(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public NeoPeople() {
        super();
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
