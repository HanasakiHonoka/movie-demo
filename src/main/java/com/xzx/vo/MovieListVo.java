package com.xzx.vo;

import com.xzx.dto.MovieWithPeople;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "电影列表返回参数", description = "电影列表返回参数")
public class MovieListVo {

    @ApiModelProperty(value = "电影列表")
    private List<MovieWithPeople> movies;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "电影总数")
    private long size;

}
