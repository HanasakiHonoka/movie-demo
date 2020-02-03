package com.xzx.servie;

import com.xzx.entity.User;

import java.util.List;

public interface UserService {

    public User login(User user);

    public Integer loginUpdate(User user);

    public List<User> getUsers();

    public Integer delUser(Integer userId);

    public Integer insertUser(User user);

    public Integer UpdateUser(User user);

    public User getUserByName(String userName);

    public User getUserById(Integer userId);
}
