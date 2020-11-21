package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Classname Dictionary
 * @Description
 * @Date 2020/11/19 20:16
 * @Author XZX
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dictionary")
@ApiModel(value="字典表", description="")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    private String dicType;
    private String dicNo;
    private String dicName;

    public static final String DIC_TYPE = "dic_type";
    public static final String DIC_NO = "dic_no";
    public static final String DIC_NAME = "dic_name";

}
