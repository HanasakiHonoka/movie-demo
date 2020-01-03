package com.xzx.servie;

import com.xzx.entity.Actor;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import com.xzx.dto.SimpleActor;

import java.util.List;

public interface ActorService {

    public List<Actor> getLikeActorWithLimit(SearchVo searchVo);

    public List<Actor> getFirstLikeActors(HintVo hintVo);

    public long getLikeActorCount(SearchVo searchVo);

    public Actor getActor(Integer id);

    public List<SimpleActor> getSimpleActorByMovieId(Integer movieId);

    public List<Actor> getActorByName(String name);
}
