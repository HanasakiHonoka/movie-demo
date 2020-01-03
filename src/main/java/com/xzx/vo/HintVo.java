package com.xzx.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "提示请求参数", description = "提示请求参数")
public class HintVo {

    @ApiModelProperty(value = "类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "搜索关键词", example = "战")
    private String words;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public HintVo() {
        super();
    }

    public HintVo(Integer type, String words) {
        this.type = type;
        this.words = words;
    }
}
