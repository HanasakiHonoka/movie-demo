package com.xzx.controller;

import com.xzx.entity.User;
import com.xzx.servie.UserService;
import com.xzx.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "用户模块")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public LoginVo Login(User user) {
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
}
