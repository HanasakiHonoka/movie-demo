package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_table")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户角色：1--普通用户，2--管理员")
    private Boolean role;

    @ApiModelProperty(value = "账户状态：1--登录，2--未登录")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime loginTime;


}
