package com.xzx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzx.constant.MovieTypeEnum;
import com.xzx.dto.SimpleActor;
import com.xzx.dto.SimpleDirector;
import com.xzx.dto.SimpleScenarist;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "票房预测请求参数", description = "票房预测请求参数")
public class BoxCalculateVo {

    @ApiModelProperty(value = "是否使用Neo4j", example = "false")
    private boolean useNeo4j = false;

    @ApiModelProperty(value = "电影类型", example = "喜剧")
    private List<String> type;
    @ApiModelProperty(value = "电影时长", example = "120")
    private Integer duration;
    @ApiModelProperty(value = "上映时间", example = "2020-10-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date releaseTime;
    @ApiModelProperty(value = "是否为Ip", example = "false")
    private Boolean isIp;
    @ApiModelProperty(value = "是否续集", example = "false")
    private Boolean isSequel;
    @ApiModelProperty(value = "电影预算", example = "1")
    private String budget;
    @ApiModelProperty(value = "电影制式", example = "2D")
    private String technology;
    @ApiModelProperty(value = "导演列表", example = "1000525")
    private List<Integer> directors;
    @ApiModelProperty(value = "演员列表")
    private List<Integer> actors;
    @ApiModelProperty(value = "编剧列表")
    private List<Integer> scenarists;
    @ApiModelProperty(value = "issueId")
    private Integer issueId;
    @ApiModelProperty(value = "manuId")
    private Integer manuId;

}