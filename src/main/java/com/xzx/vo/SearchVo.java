package com.xzx.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "搜索请求参数", description = "搜索请求参数")
public class SearchVo {

    @ApiModelProperty(value = "类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "搜索关键词", example = "战")
    private String words;
    @ApiModelProperty(value = "搜索页数", example = "1")
    private String page;

    public SearchVo() {
        super();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public SearchVo(Integer type, String words, String page) {
        this.type = type;
        this.words = words;
        this.page = page;
    }

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
}
