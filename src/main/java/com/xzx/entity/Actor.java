package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("actor_table")
@ApiModel(value="Actor对象", description="")
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @CsvBindByName
    @ApiModelProperty(value = "演员id")
    private Integer id;

    @CsvBindByName
    @ApiModelProperty(value = "姓名")
    private String name;

    @CsvBindByName
    @ApiModelProperty(value = "性别")
    private String gender;

    @CsvBindByName
    @ApiModelProperty(value = "职业")
    private String occupation;

    @CsvBindByName
    @ApiModelProperty(value = "星座")
    private String constellation;

    @CsvCustomBindByName(column = "birthday", converter = ConvertStringToDate.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @CsvBindByName
    @ApiModelProperty(value = "出生地区")
    private String location;

    @CsvBindByName
    @ApiModelProperty(value = "戏龄")
    private Integer actAge;

    @CsvBindByName
    @ApiModelProperty(value = "表演风格")
    private String actStyle;

    @CsvBindByName
    @ApiModelProperty(value = "流量热度")
    private Integer popularity;

    @ApiModelProperty(value = "指导电影数目")
    private Integer amount;

    private Integer baiduIndex;
    private Integer boxoffice;
    private Float prBoxRank;

    @ApiModelProperty(value = "图片名")
    private String imageName;


}
