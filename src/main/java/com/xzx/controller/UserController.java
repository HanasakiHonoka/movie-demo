package com.xzx.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.xzx.dto.SimpleUser;
import com.xzx.entity.User;
import com.xzx.servie.IUserService;
import com.xzx.util.SimpleUserUtil;
import com.xzx.vo.LoginVo;
import com.xzx.vo.UpdatePasswordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Api(tags = "用户模块")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public LoginVo login(User user) {
        LoginVo loginVo = new LoginVo();
        // 密码加密
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        User currUser = userService.login(user);
        if (currUser != null) {
            currUser.setLoginTime(LocalDateTime.now());
            //currUser.setStatus(1);
            loginVo.setUser(currUser);
            userService.updateById(currUser);
            loginVo.setMsg(1);
        } else {
            loginVo.setMsg(0);
        }
        return loginVo;
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/user/updatePwd")
    public boolean updatePassword(UpdatePasswordVo updatePasswordVo) {
        User user = userService.getById(updatePasswordVo.getId());
        String pastPwd = DigestUtil.md5Hex(updatePasswordVo.getPastPassword());
        String nowPwd = DigestUtil.md5Hex(updatePasswordVo.getNowPassword());
        // 密码加密
        if(user.getPassword().equals(pastPwd)) {
            user.setPassword(nowPwd);
            return userService.updateById(user);
        } else {
            return false;
        }
    }

    @ApiOperation(value = "获得所有普通用户")
    @GetMapping("/users")
    public List<SimpleUser> getUsers() {
        return SimpleUserUtil.userToSimpleUser(userService.list());
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/user/{id}")
    public boolean delUser(@PathVariable(value = "id") String userId) {
        return userService.removeById(Integer.parseInt(userId));
    }

    @ApiOperation(value = "添加普通用户")
    @PutMapping("/user")
    public boolean insertUser(User user) {
        User user1 = userService.getUserByName(user.getUsername());
        if(user1 == null) {
            user.setRole(false);
            user.setRegisterTime(LocalDateTime.now());
            // 密码加密
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
            return userService.save(user);
        } else {
            return false;
        }
    }





}
