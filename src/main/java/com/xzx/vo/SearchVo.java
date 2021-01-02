package com.xzx.vo;

import com.xzx.constant.ConstantParam;
import com.xzx.constant.SearchTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "搜索请求参数", description = "搜索请求参数")
public class SearchVo {

    @ApiModelProperty(value = "类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "搜索关键词", example = "战")
    private String words;
    @ApiModelProperty(value = "搜索页数", example = "1")
    private String page;
    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = ConstantParam.DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "演员数量")
    private Integer actorNum;
    @ApiModelProperty(value = "导演数量")
    private Integer directorNum;
    @ApiModelProperty(value = "编剧数量")
    private Integer scenaristNum;
    @ApiModelProperty(value = "评分0降序1升序",required = false)
    Boolean doubanRating;
    @ApiModelProperty(value = "票房0降序1升序",required = false)
    Boolean boxoffice;
    @ApiModelProperty(value = "上映日期0降序1升序",required = false)
    Boolean releaseTime;

    public void setDefaultValue() {
        SearchTypeEnum type = SearchTypeEnum.getByValue(this.type);
        if (type == SearchTypeEnum.MOVIE) {
            this.actorNum = this.actorNum == null ? ConstantParam.DEFAULT_MOVIE_ACTOR_NUM : this.actorNum;
            this.directorNum = this.directorNum == null ? ConstantParam.DEFAULT_MOVIE_DIRECTOR_NUM : this.directorNum;
            this.scenaristNum = this.scenaristNum == null ? ConstantParam.DEFAULT_MOVIE_SCENARIST_NUM : this.scenaristNum;
        } else {
            this.actorNum = this.actorNum == null ? ConstantParam.DEFAULT_PERSON_ACTOR_NUM : this.actorNum;
            this.directorNum = this.directorNum == null ? ConstantParam.DEFAULT_PERSON_DIRECTOR_NUM : this.directorNum;
            this.scenaristNum = this.scenaristNum == null ? ConstantParam.DEFAULT_PERSON_SCENARIST_NUM : this.scenaristNum;
        }
    }
}