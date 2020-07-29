package com.xzx.vo;

import com.xzx.constant.ConstantParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName Neo4jMovieVo
 * @Description
 * @Author XZX
 * @Date 2020/7/30 1:35
 * @Version V1.0
 **/
@Data
@ApiModel(value = "以电影为中心的查询参数", description = "以电影为中心的查询参数")
public class Neo4jMovieVo {

    @ApiModelProperty(value = "电影id", example = "2931")
    private String id;

    @ApiModelProperty(value = "电影演员数")
    private Integer actorNum = ConstantParam.DEFAULT_MOVIE_ACTOR_NUM;
    @ApiModelProperty(value = "电影导演数")
    private Integer directorNum = ConstantParam.DEFAULT_MOVIE_DIRECTOR_NUM;
    @ApiModelProperty(value = "电影编剧数")
    private Integer scenaristNum = ConstantParam.DEFAULT_MOVIE_SCENARIST_NUM;
}
