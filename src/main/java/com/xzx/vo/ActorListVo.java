package com.xzx.vo;

import com.xzx.entity.Actor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "演员列表返回参数", description = "演员列表返回参数")
public class ActorListVo {

    @ApiModelProperty(value = "演员列表")
    private List<Actor> actors;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "演员总数")
    private long size;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
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
