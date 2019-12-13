package com.xzx.servie;

import com.xzx.entity.Actor;
import com.xzx.vo.SearchVo;

import java.util.List;

public interface ActorService {

    public List<Actor> getLikeActorWithLimit(SearchVo searchVo);

    public long getLikeActorCount(SearchVo searchVo);

    public Actor getActor(Integer id);
}
