package com.xzx.vo;

import com.xzx.dto.ScenaristWithMovie;

import java.util.List;

public class MgtScenaristListVo {

    private List<ScenaristWithMovie> scenarists;

    public List<ScenaristWithMovie> getScenarists() {
        return scenarists;
    }

    public void setScenarists(List<ScenaristWithMovie> scenarists) {
        this.scenarists = scenarists;
    }

    public MgtScenaristListVo(List<ScenaristWithMovie> scenarists) {
        this.scenarists = scenarists;
    }

    public MgtScenaristListVo() {
    }
}
