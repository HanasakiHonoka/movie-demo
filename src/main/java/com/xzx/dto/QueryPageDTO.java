package com.xzx.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname QueryPageDTO
 * @Description
 * @Date 2020/10/30 12:38
 * @Author XZX
 * @Version 1.0
 */
@Data
public class QueryPageDTO {
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数",required = false)
    private Integer page = 1;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量",required = false)
    private Integer pageSize = 10;
}
