package com.xzx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Classname ActorParticipate
 * @Description
 * @Date 2020/11/6 22:12
 * @Author XZX
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("actor_participate")
@ApiModel(value="Actor对象", description="")
public class ActorParticipate {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer actorId;
    private Integer movieId;
    @TableField("`rank`")
    private Integer rank;
    private String actStyle;
    private String role;
    private String roleCharacter;
    private Integer roleAge;
    private String roleOccupation;
    private String roleGender;
    private String roleLocalism;
    private String roleApperaence;
    private String roleSet;

    public static final String ACTOR_ID = "actor_id";
    public static final String MOVIE_ID = "movie_id";
}
