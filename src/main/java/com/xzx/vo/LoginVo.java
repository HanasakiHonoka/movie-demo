package com.xzx.vo;

import com.xzx.entity.User;
import lombok.Data;

@Data
public class LoginVo {

    private User user;
    private Integer msg;
}
