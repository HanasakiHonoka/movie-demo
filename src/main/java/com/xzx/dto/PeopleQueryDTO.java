package com.xzx.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname PeopleQueryDTO
 * @Description
 * @Date 2020/11/6 21:52
 * @Author XZX
 * @Version 1.0
 */
@Data
public class PeopleQueryDTO extends QueryPageDTO{
    @ApiModelProperty(value = "性别0女1男",required = false)
    Boolean gender;
    @ApiModelProperty(value = "生日0降序1升序",required = false)
    Boolean birthday;
}
