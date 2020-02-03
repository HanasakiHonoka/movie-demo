package com.xzx.servie.impl;

import com.xzx.entity.User;
import com.xzx.entity.UserExample;
import com.xzx.mapper.UserMapper;
import com.xzx.servie.UserService;
import io.swagger.annotations.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            return null;
        } else {
            User resUser = users.get(0);
            //resUser.setLoginTime(new Date());
            return resUser;
        }
    }

    @Override
    public Integer loginUpdate(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> getUsers() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andRoleEqualTo(false);
        return userMapper.selectByExample(example);
    }

    @Override
    public Integer delUser(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer UpdateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User getUserByName(String userName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0 ) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
