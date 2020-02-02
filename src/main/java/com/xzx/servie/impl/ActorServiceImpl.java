package com.xzx.servie.impl;

import com.xzx.entity.Actor;
import com.xzx.entity.ActorExample;
import com.xzx.mapper.ActorExtendMapper;
import com.xzx.mapper.ActorMapper;
import com.xzx.servie.ActorService;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import com.xzx.dto.SimpleActor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private ActorExtendMapper actorExtendMapper;

    @Override
    public List<Actor> getLikeActorWithLimit(SearchVo searchVo) {
        ActorExample example = new ActorExample();
        ActorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds((Integer.parseInt(searchVo.getPage()) - 1) * 10,10);
        return actorMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<Actor> getFirstLikeActors(HintVo hintVo) {
        ActorExample example = new ActorExample();
        ActorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike(hintVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds(0,10);
        return actorMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public long getLikeActorCount(SearchVo searchVo) {
        ActorExample example = new ActorExample();
        ActorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        return actorMapper.countByExample(example);
    }

    @Override
    public Actor getActor(Integer id) {
        return actorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Actor> getActors() {
        return actorMapper.selectByExample(new ActorExample());
    }

    @Override
    public Integer updateActor(Actor actor) {
        return actorMapper.updateByPrimaryKey(actor);
    }

    @Override
    public Integer insertActor(Actor actor) {
        return actorMapper.insert(actor);
    }

    @Override
    public Integer delActor(Integer actorId) {
        return actorMapper.deleteByPrimaryKey(actorId);
    }

    @Override
    public List<SimpleActor> getSimpleActorByMovieId(Integer movieId) {
        List<Actor> actors = actorExtendMapper.getActorByMovieId(movieId);
        List<SimpleActor> simpleActors = new ArrayList<>();
        for (Actor actor: actors) {
            simpleActors.add(new SimpleActor(actor.getId(), actor.getName()));
        }
        return simpleActors;
    }

    @Override
    public List<Actor> getActorByName(String name) {
        ActorExample example = new ActorExample();
        ActorExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        return actorMapper.selectByExample(example);
    }
}
