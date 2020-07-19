package com.xzx.vo;

import com.xzx.dto.DirectorWithMovie;
import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "导演列表返回参数", description = "导演列表返回参数")
public class DirectorListVo {

    @ApiModelProperty(value = "导演列表")
    private List<DirectorWithMovie> directors;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "导演总数")
    private long size;

}
