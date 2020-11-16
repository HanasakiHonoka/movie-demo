package com.xzx.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Classname RecommendVo
 * @Description
 * @Date 2020/8/31 19:13
 * @Author XZX
 * @Version 1.0
 */
@Data
public class RecommendVo {

    private String id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String rawScore;
    private Integer score;

}
