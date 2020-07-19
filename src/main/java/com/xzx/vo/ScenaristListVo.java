package com.xzx.vo;

import com.xzx.dto.ScenaristWithMovie;
import com.xzx.entity.Scenarist;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "编剧列表返回参数", description = "编剧列表返回参数")
public class ScenaristListVo {

    @ApiModelProperty(value = "编剧列表")
    private List<ScenaristWithMovie> scenarists;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "编剧总数")
    private long size;

}
