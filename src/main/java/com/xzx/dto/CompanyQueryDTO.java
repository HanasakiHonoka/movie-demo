package com.xzx.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname CompanyQueryDTO
 * @Description
 * @Date 2020/11/29 11:19
 * @Author XZX
 * @Version 1.0
 */
@Data
public class CompanyQueryDTO {

    @ApiModelProperty("公司ID")
    private Integer companyId;
    @ApiModelProperty("公司名称")
    private String companyName;
}
