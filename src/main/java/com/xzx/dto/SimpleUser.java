package com.xzx.dto;

public class SimpleUser {

    private Integer id;
    private String userName;

    public SimpleUser(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public SimpleUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
