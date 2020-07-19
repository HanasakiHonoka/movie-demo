package com.xzx.vo;

import lombok.Data;

@Data
public class UpdatePasswordVo {

    private int id;
    private String pastPassword;
    private String nowPassword;

}
