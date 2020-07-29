package com.xzx.vo;

import com.xzx.constant.ConstantParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName Neo4jPersonVo
 * @Description
 * @Author XZX
 * @Date 2020/7/30 1:34
 * @Version V1.0
 **/
@Data
@ApiModel(value = "以人物为中心的查询参数", description = "其中电影xx数指第二层关系中的电影数")
public class Neo4jPersonVo {

    @ApiModelProperty(value = "人物id", example = "1274297", required = true)
    private String id;

    @ApiModelProperty(value = "参演电影数")
    private Integer actorNum = ConstantParam.NEO_DEFAULT_PERSON_ACTOR_NUM;
    @ApiModelProperty(value = "导演电影数")
    private Integer directorNum = ConstantParam.NEO_DEFAULT_PERSON_DIRECTOR_NUM;
    @ApiModelProperty(value = "编写电影数")
    private Integer scenaristNum = ConstantParam.NEO_DEFAULT_PERSON_SCENARIST_NUM;
    @ApiModelProperty(value = "电影演员数")
    private Integer mActorNum = ConstantParam.NEO_DEFAULT_M_PERSON_ACTOR_NUM;
    @ApiModelProperty(value = "电影导演数")
    private Integer mDirectorNum = ConstantParam.NEO_DEFAULT_M_PERSON_DIRECTOR_NUM;
    @ApiModelProperty(value = "电影编剧数")
    private Integer mScenaristNum = ConstantParam.NEO_DEFAULT_M_PERSON_SCENARIST_NUM;
}
