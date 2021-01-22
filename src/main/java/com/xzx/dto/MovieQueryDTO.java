package com.xzx.dto;

import com.xzx.constant.MovieTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname MovieQueryDTO
 * @Description
 * @Date 2020/10/30 12:41
 * @Author XZX
 * @Version 1.0
 */
@Data
public class MovieQueryDTO extends QueryPageDTO{
    @ApiModelProperty(value = "标题",required = false)
    String title;
    @ApiModelProperty(value = "类型",required = false)
    String type;
    @ApiModelProperty(value = "票房0降序1升序",required = false)
    Boolean boxoffice;
    @ApiModelProperty(value = "上映日期0降序1升序",required = false)
    Boolean releaseTime;
}
