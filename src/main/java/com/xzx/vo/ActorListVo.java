package com.xzx.vo;

import com.xzx.dto.ActorWithMovie;
import com.xzx.entity.Actor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "演员列表返回参数", description = "演员列表返回参数")
public class ActorListVo {

    @ApiModelProperty(value = "演员列表")
    private List<ActorWithMovie> actors;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "演员总数")
    private long size;
}
