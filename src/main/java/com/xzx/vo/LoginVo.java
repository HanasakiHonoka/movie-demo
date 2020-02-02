package com.xzx.vo;

import com.xzx.entity.User;

public class LoginVo {


    private User user;
    private Integer msg;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }
}
