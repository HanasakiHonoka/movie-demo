package com.xzx.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "提示请求参数", description = "提示请求参数")
public class HintVo {

    @ApiModelProperty(value = "类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "搜索关键词", example = "战")
    private String words;

}
