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
    @ApiModelProperty(value = "电影票房")
    private Float boxoffice;

    @CsvBindByName
    @ApiModelProperty(value = "首周票房")
    private Float firstBoxoffice;

    private Float prMovie;
    private Float btMovie;
    private Float clMovie;
    private Float deMovie;
    private Float prBoxRank;
    private Float doubanRating;
    private Integer baiduIndex;
    private Integer ratingNumbers;
    private Integer commentNumbers;
    private Integer reviewNumbers;

    @ApiModelProperty(value = "图片名")
    private String imageName;



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

    @TableField("director_1_pr_boxrank")
    private Float director1PrBoxrank;

    @TableField("director_1_baidu")
    private Integer director1Baidu;

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

    @TableField("director_2_pr_boxrank")
    private Float director2PrBoxrank;

    @TableField("director_2_baidu")
    private Integer director2Baidu;

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

    @TableField("scenarist_1_pr_boxrank")
    private Float scenarist1PrBoxrank;

    @TableField("scenarist_1_baidu")
    private Integer scenarist1Baidu;

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

    @TableField("scenarist_2_pr_boxrank")
    private Float scenarist2PrBoxrank;

    @TableField("scenarist_2_baidu")
    private Integer scenarist2Baidu;

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

    @TableField("actor_1_pr_boxrank")
    private Float actor1PrBoxrank;

    @TableField("actor_1_baidu")
    private Integer actor1Baidu;

    @TableField("actor_1_fans")
    private Integer actor1Fans;

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

    @TableField("actor_2_pr_boxrank")
    private Float actor2PrBoxrank;

    @TableField("actor_2_baidu")
    private Integer actor2Baidu;

    @TableField("actor_2_fans")
    private Integer actor2Fans;

    @TableField("issue_company_1_id")
    private Integer issueCompany1Id;

    @TableField("manu_company_1_id")
    private Integer manuCompany1Id;


    public static final String TYPE = "type";
    public static final String BOXOFFICE = "boxoffice";
    public static final String RELEASE_TIME = "release_time";
    public static final String DOUBAN_RATING = "douban_rating";

}
