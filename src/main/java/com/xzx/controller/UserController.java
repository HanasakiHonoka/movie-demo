package com.xzx.controller;

import com.xzx.dto.SimpleUser;
import com.xzx.entity.User;
import com.xzx.servie.UserService;
import com.xzx.util.SimpleUserUtil;
import com.xzx.vo.LoginVo;
import com.xzx.vo.UpdatePasswordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "用户模块")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public LoginVo login(User user) {
        LoginVo loginVo = new LoginVo();
        User currUser = userService.login(user);
        if (currUser != null) {
            currUser.setLoginTime(new Date());
            //currUser.setStatus(1);
            loginVo.setUser(currUser);
            userService.loginUpdate(currUser);
            loginVo.setMsg(1);
        } else {
            loginVo.setMsg(0);
        }
        return loginVo;
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/user/updatePwd")
    public Integer updatePassword(UpdatePasswordVo updatePasswordVo) {
        User user = userService.getUserById(updatePasswordVo.getId());
        if(user.getPassword().equals(updatePasswordVo.getPastPassword())) {
            user.setPassword(updatePasswordVo.getNowPassword());
            return userService.UpdateUser(user);
        } else {
            return 2;
        }
    }

    @ApiOperation(value = "获得所有普通用户")
    @GetMapping("/users")
    public List<SimpleUser> getUsers() {
        return SimpleUserUtil.userToSimpleUser(userService.getUsers());
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/user/{id}")
    public Integer delUser(@PathVariable(value = "id") String userId) {
        return userService.delUser(Integer.parseInt(userId));
    }

    @ApiOperation(value = "添加普通用户")
    @PutMapping("/user")
    public Integer insertUser(User user) {
        User user1 = userService.getUserByName(user.getUsername());
        if(user1 == null) {
            user.setRole(false);
            user.setRegisterTime(new Date());
            return userService.insertUser(user);
        } else {
            return 2;
        }
    }





}
