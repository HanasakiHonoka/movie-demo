package com.xzx.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname DictionaryQueryDTO
 * @Description
 * @Date 2020/11/19 20:18
 * @Author XZX
 * @Version 1.0
 */
@Data
public class DictionaryQueryDTO {
    @ApiModelProperty("职业:occupation;人设:characterSet;")
    private String dicType;
    private String dicNo;
    private String dicName;
}
