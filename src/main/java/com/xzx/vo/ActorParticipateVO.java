package com.xzx.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Classname ActorParticipateVO
 * @Description
 * @Date 2020/11/19 19:56
 * @Author XZX
 * @Version 1.0
 */
@Data
public class ActorParticipateVO {
    private Integer id;
    private Integer actorId;
    private String actorName;
    private Integer movieId;
    private Integer rank;
    private String actStyle;
    private String role;
    private String roleCharacter;
    private Integer roleAge;
    private String roleOccupation;
    private String roleOccupationName;
    private String roleGender;
    private String roleLocalism;
    private String roleApperaence;
    private String roleSet;
    private String roleSetName;
}
