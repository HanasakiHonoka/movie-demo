package com.xzx.vo;

import com.xzx.entity.Movie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "电影列表返回参数", description = "电影列表返回参数")
public class MovieListVo {

    @ApiModelProperty(value = "电影列表")
    private List<Movie> movies;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "电影总数")
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
