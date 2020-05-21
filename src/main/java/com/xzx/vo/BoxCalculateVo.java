package com.xzx.vo;

import com.xzx.dto.SimpleActor;
import com.xzx.dto.SimpleDirector;
import com.xzx.dto.SimpleScenarist;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value = "票房预测请求参数", description = "票房预测请求参数")
public class BoxCalculateVo {

    @ApiModelProperty(value = "电影类型", example = "戏剧")
    private Integer type;
    @ApiModelProperty(value = "电影时长", example = "120")
    private Integer duration;
    @ApiModelProperty(value = "上映时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseTime;
    @ApiModelProperty(value = "是否为Ip")
    private Boolean isIp;
    @ApiModelProperty(value = "是否续集")
    private Boolean isSequel;
    @ApiModelProperty(value = "电影预算", example = "1")
    private Integer budget;
    @ApiModelProperty(value = "电影制式", example = "2D")
    private Integer technology;
    @ApiModelProperty(value = "导演列表")
    private List<Integer> directors;
    @ApiModelProperty(value = "演员列表")
    private List<Integer> actors;
    @ApiModelProperty(value = "编剧列表")
    private List<Integer> scenarists;

    public List<Integer> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Integer> directors) {
        this.directors = directors;
    }

    public List<Integer> getActors() {
        return actors;
    }

    public void setActors(List<Integer> actors) {
        this.actors = actors;
    }

    public List<Integer> getScenarists() {
        return scenarists;
    }

    public void setScenarists(List<Integer> scenarists) {
        this.scenarists = scenarists;
    }

    public Boolean getIsIp() {
        return isIp;
    }

    public void setIsIp(Boolean isIp) {
        this.isIp = isIp;
    }

    public Boolean getIsSequel() {
        return isSequel;
    }

    public void setIsSequel(Boolean isSequel) {
        this.isSequel = isSequel;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }


    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        this.technology = technology;
    }
}