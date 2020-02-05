package com.xzx.mapper;

import com.xzx.entity.Actor;

import java.util.List;

public interface ActorExtendMapper {

    public List<Actor> getActorByMovieId(Integer movieId);

    public Integer insertMulti(List<Actor> actors);
}
