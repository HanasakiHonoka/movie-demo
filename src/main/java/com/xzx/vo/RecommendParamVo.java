package com.xzx.vo;

import com.xzx.constant.ConstantParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname RecommendParamVo
 * @Description
 * @Date 2020/8/31 19:42
 * @Author XZX
 * @Version 1.0
 */
@Data
@ApiModel(value = "角色推荐请求参数", description = "角色推荐请求参数")
public class RecommendParamVo {

    @ApiModelProperty(value = "导演id列表")
    private List<Integer> directorId;
    @ApiModelProperty(value = "编剧id列表")
    private List<Integer> scenaristId;
    @ApiModelProperty(value = "演员id列表")
    private List<Integer> actorId;

    @ApiModelProperty(value = "返回结果数量(默认10条)")
    private Integer peopleNum = ConstantParam.RECOMMEND_PEOPLE_NUM;
}
