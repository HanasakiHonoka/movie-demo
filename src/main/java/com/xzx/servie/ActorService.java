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

    public long getActorCount();

    public Actor getActor(Integer id);

    public List<Actor> getActors();

    public Integer updateActor(Actor actor);

    public Integer insertActor(Actor actor);

    public Integer insertMulti(List<Actor> actors);

    public Integer delActor(Integer actorId);

    public List<SimpleActor> getSimpleActorByMovieId(Integer movieId);

    public List<Actor> getActorByName(String name);
}
