package com.xzx.vo;

public class HintResVo {

    private String value;
    private Integer id;



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HintResVo() {
        super();
    }

    public HintResVo(Integer id, String value) {
        this.value = value;
        this.id = id;
    }
}
