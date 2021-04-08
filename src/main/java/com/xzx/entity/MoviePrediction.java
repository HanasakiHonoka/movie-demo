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

import java.time.LocalDate;

/**
 * @ClassName MoviePrediction
 * @Description
 * @Author XZX
 * @Date 2021/1/10 15:32
 * @Version V1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("movie_prediction_since2019")
@ApiModel(value="MoviePrediction对象", description="")
public class MoviePrediction {
    private static final long serialVersionUID = 1L;

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

    //@ApiModelProperty(value = "语言")
    //private String language;

    //@ApiModelProperty(value = "上映地区")
    //private String releaseArea;

    //@ApiModelProperty(value = "电影制式")
    //private String technology;

    @ApiModelProperty(value = "电影类型")
    private String type;

    @ApiModelProperty(value = "电影现有票房")
    private Float boxoffice;

    @ApiModelProperty(value = "电影封面")
    private String imageName;

    @ApiModelProperty(value = "电影预测票房")
    @TableField("predicted_boxoffice")
    private Float expectedBoxoffice;

    @ApiModelProperty(value = "电影状态")
    private Integer state;

    private Float accuracy;

    public static final String EXPECTED_BOXOFFICE = "predicted_boxoffice";

}