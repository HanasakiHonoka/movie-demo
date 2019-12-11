package com.xzx.vo;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "返回类")
public class ResultVo<T> {

    private T data;

    public ResultVo(T data) {
        this.data = data;
    }
    public ResultVo() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
