package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.entity.User;
import com.xzx.mapper.UserMapper;
import com.xzx.servie.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());
        List<User> userList = this.list(wrapper);
        if (userList.size() == 0) {
            return null;
        } else {
            User resUser = userList.get(0);
            return resUser;
        }
    }

    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<User> userList = this.list(wrapper);
        if (userList.size() == 0) {
            return null;
        } else {
            User resUser = userList.get(0);
            return resUser;
        }
    }
}
