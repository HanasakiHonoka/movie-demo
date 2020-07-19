package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.xzx.convert.ConvertStringToDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("movie_table")
@ApiModel(value="Movie对象", description="")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @CsvBindByName
    @ApiModelProperty(value = "电影id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @CsvBindByName
    @ApiModelProperty(value = "电影名称")
    private String title;

    @CsvBindByName
    @ApiModelProperty(value = "电影时长")
    private Integer duration;

    @CsvCustomBindByName(column = "release_time", converter = ConvertStringToDate.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "上映日期")
    private LocalDate releaseTime;

    @CsvBindByName
    @ApiModelProperty(value = "上映地区")
    private String releaseArea;

    @CsvBindByName
    @ApiModelProperty(value = "电影制式")
    private String technology;

    @CsvBindByName
    @ApiModelProperty(value = "电影类型")
    private String type;

    @CsvBindByName
    @ApiModelProperty(value = "二级类型")
    private String secondType;

    @CsvBindByName
    @ApiModelProperty(value = "电影票房")
    private Float boxoffice;

    @CsvBindByName
    @ApiModelProperty(value = "首周票房")
    private Float firstBoxoffice;

    @CsvBindByName
    @ApiModelProperty(value = "演员")
    private String actor;

    @CsvBindByName
    @ApiModelProperty(value = "发行公司")
    private String issueCompany;

    @CsvBindByName
    @ApiModelProperty(value = "制作公司")
    private String manuCompany;

    @CsvBindByName
    @ApiModelProperty(value = "是否为IP电影")
    private Boolean isIp;

    @CsvBindByName
    @ApiModelProperty(value = "是否为续集")
    private Boolean isSequel;

    @CsvBindByName
    @ApiModelProperty(value = "是否为网络电影")
    private Boolean isNetwork;

    @CsvBindByName
    @TableField("director_1_id")
    private Integer director1Id;

    @CsvBindByName
    @TableField("director_1_name")
    private String director1Name;

    @CsvBindByName
    @TableField("director_1_amount")
    private Integer director1Amount;

    @CsvBindByName
    @TableField("director_1_boxoffice")
    private Float director1Boxoffice;

    @CsvBindByName
    @TableField("director_2_id")
    private Integer director2Id;

    @CsvBindByName
    @TableField("director_2_name")
    private String director2Name;

    @CsvBindByName
    @TableField("director_2_amount")
    private Integer director2Amount;

    @CsvBindByName
    @TableField("director_2_boxoffice")
    private Float director2Boxoffice;

    @CsvBindByName
    @TableField("scenarist_1_id")
    private Integer scenarist1Id;

    @CsvBindByName
    @TableField("scenarist_1_name")
    private String scenarist1Name;

    @CsvBindByName
    @TableField("scenarist_1_amount")
    private Integer scenarist1Amount;

    @CsvBindByName
    @TableField("scenarist_1_boxoffice")
    private Float scenarist1Boxoffice;

    @CsvBindByName
    @TableField("scenarist_2_id")
    private Integer scenarist2Id;

    @CsvBindByName
    @TableField("scenarist_2_name")
    private String scenarist2Name;

    @CsvBindByName
    @TableField("scenarist_2_amount")
    private Integer scenarist2Amount;

    @CsvBindByName
    @TableField("scenarist_2_boxoffice")
    private Float scenarist2Boxoffice;

    @CsvBindByName
    @TableField("actor_1_id")
    private Integer actor1Id;

    @CsvBindByName
    @TableField("actor_1_name")
    private String actor1Name;

    @CsvBindByName
    @TableField("actor_1_amount")
    private Integer actor1Amount;

    @CsvBindByName
    @TableField("actor_1_boxoffice")
    private Float actor1Boxoffice;

    @CsvBindByName
    @TableField("actor_2_id")
    private Integer actor2Id;

    @CsvBindByName
    @TableField("actor_2_name")
    private String actor2Name;

    @CsvBindByName
    @TableField("actor_2_amount")
    private Integer actor2Amount;

    @CsvBindByName
    @TableField("actor_2_boxoffice")
    private Float actor2Boxoffice;


}
