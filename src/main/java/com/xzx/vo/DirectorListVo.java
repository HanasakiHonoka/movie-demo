package com.xzx.vo;

import com.xzx.dto.DirectorWithMovie;
import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "导演列表返回参数", description = "导演列表返回参数")
public class DirectorListVo {

    @ApiModelProperty(value = "导演列表")
    private List<DirectorWithMovie> directors;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "导演总数")
    private long size;

    public List<DirectorWithMovie> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorWithMovie> directors) {
        this.directors = directors;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
