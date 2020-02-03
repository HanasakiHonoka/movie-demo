package com.xzx.util;

import com.xzx.dto.SimpleUser;
import com.xzx.entity.User;

import java.util.ArrayList;
import java.util.List;

public class SimpleUserUtil {

    public static List<SimpleUser> userToSimpleUser(List<User> users) {
        List<SimpleUser> simpleUsers = new ArrayList<>();
        for (User user:users) {
            SimpleUser simpleUser = new SimpleUser();
            simpleUser.setId(user.getId());
            simpleUser.setUserName(user.getUsername());
            simpleUsers.add(simpleUser);
        }
        return simpleUsers;
    }
}
