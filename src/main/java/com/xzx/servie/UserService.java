package com.xzx.servie;

import com.xzx.entity.User;

public interface UserService {

    public User login(User user);

    public Integer loginUpdate(User user);
}
