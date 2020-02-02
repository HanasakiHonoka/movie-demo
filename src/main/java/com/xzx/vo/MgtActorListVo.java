package com.xzx.vo;

import com.xzx.dto.ActorWithMovie;

import java.util.List;

public class MgtActorListVo {

    private List<ActorWithMovie> actors;

    public MgtActorListVo() {
    }

    public MgtActorListVo(List<ActorWithMovie> actors) {
        this.actors = actors;
    }

    public List<ActorWithMovie> getActors() {
        return actors;
    }

    public void setActors(List<ActorWithMovie> actors) {
        this.actors = actors;
    }
}
