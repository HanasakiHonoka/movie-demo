package com.xzx.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzx.dto.SimpleActor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.xzx.dto.SimpleDirector;
import com.xzx.dto.SimpleScenarist;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName MoviePredictionPageVO
 * @Description
 * @Author XZX
 * @Date 2021/1/10 15:39
 * @Version V1.0
 **/
@Data
public class MoviePredictionPageVO {
    @ApiModelProperty(value = "电影id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "电影名称")
    private String title;

    @ApiModelProperty(value = "电影时长")
    private Integer duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "上映日期")
    private LocalDate releaseTime;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "上映地区")
    private String releaseArea;

    @ApiModelProperty(value = "电影制式")
    private String technology;

    @ApiModelProperty(value = "电影类型")
    private String type;

    @ApiModelProperty(value = "电影现有票房")
    private Float boxoffice;

    @ApiModelProperty(value = "电影预测票房")
    private Float expectedBoxoffice;

    @ApiModelProperty(value = "电影状态")
    private String state;

    @ApiModelProperty(value = "准确度")
    private Double accuracy;

    private List<SimpleActor> actors;
    private List<SimpleDirector> directors;
    private List<SimpleScenarist> scenarists;
}
