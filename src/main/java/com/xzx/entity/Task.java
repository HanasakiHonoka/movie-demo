package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xzx.dto.BoxCalculateDto;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname Task
 * @Description
 * @Date 2020/9/14 19:54
 * @Author XZX
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("task_table")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("request")
    private String request;

    private String response;

    private Integer status;

    private LocalDateTime startTime;

    private LocalDateTime updateTime;
}
