package com.xzx.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MoviePreQueryDTO
 * @Description
 * @Author XZX
 * @Date 2021/1/30 13:20
 * @Version V1.0
 **/
@Data
public class MoviePreQueryDTO extends QueryPageDTO {

    @ApiModelProperty(value = "标题",required = false)
    String title;
    @ApiModelProperty(value = "类型",required = false)
    String type;
    @ApiModelProperty(value = "票房0降序1升序",required = false)
    Boolean boxoffice;
    @ApiModelProperty(value = "上映日期0降序1升序",required = false)
    Boolean releaseTime;
    @ApiModelProperty(value = "影片状态, 1:映后已下线, 2:状态异常, 3:未上映",required = false)
    Integer state;
}