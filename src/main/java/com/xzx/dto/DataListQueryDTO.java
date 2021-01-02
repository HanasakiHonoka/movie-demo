package com.xzx.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname DataListQueryDTO
 * @Description
 * @Date 2020/12/30 0:09
 * @Author XZX
 * @Version 1.0
 */
@Data
public class DataListQueryDTO {
    @ApiModelProperty(value = "电影类型",required = false)
    String type;
    @ApiModelProperty(value = "评分0降序1升序",required = false)
    Boolean doubanRating;
    @ApiModelProperty(value = "票房0降序1升序",required = false)
    Boolean boxoffice;
    @ApiModelProperty(value = "上映日期0降序1升序",required = false)
    Boolean releaseTime;
}
