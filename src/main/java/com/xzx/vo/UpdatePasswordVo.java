package com.xzx.vo;

public class UpdatePasswordVo {

    private int id;
    private String pastPassword;
    private String nowPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPastPassword() {
        return pastPassword;
    }

    public void setPastPassword(String pastPassword) {
        this.pastPassword = pastPassword;
    }

    public String getNowPassword() {
        return nowPassword;
    }

    public void setNowPassword(String nowPassword) {
        this.nowPassword = nowPassword;
    }
}
