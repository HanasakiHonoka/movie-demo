package com.xzx.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "搜索请求参数", description = "搜索请求参数")
public class SearchVo {

    @ApiModelProperty(value = "类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "搜索关键词", example = "战")
    private String words;
    @ApiModelProperty(value = "搜索页数", example = "1")
    private String page;

}