package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Classname Company
 * @Description
 * @Date 2020/11/29 11:53
 * @Author XZX
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("company_table")
@ApiModel(value="Company对象", description="")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer companyId;
    
    private String companyName;
    
    private Integer issueAmount;
    private Integer issueBoxoffice;
    private Integer manuAmount;
    private Integer manuBoxoffice;
    private Float pagerank;

    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANY_ID = "company_id";
}
