package com.xzx.constant;

import lombok.AllArgsConstructor;

/**
 * @ClassName MovieStateEnum
 * @Description
 * @Author XZX
 * @Date 2021/1/29 17:57
 * @Version V1.0
 **/
@AllArgsConstructor
public enum  MovieStateEnum {

    SHOWED(1, "映后已下线"),
    ERR(2, "状态异常"),
    NOT_SHOW(3, "未上映"),
    NOT_KNOW(4, "未知"),
    ;


    private Integer state;
    private String info;

    public static String getInfo(Integer state) {
        MovieStateEnum[] values = values();
        for (MovieStateEnum value : values) {
            if (value.state.equals(state)) {
                return value.info;
            }
        }
        return NOT_KNOW.info;
    }
}
