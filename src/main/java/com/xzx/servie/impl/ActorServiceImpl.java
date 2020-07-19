package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.SimpleActor;
import com.xzx.entity.Actor;
import com.xzx.mapper.ActorMapper;
import com.xzx.servie.IActorService;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements IActorService {

    @Autowired
    ActorMapper actorMapper;

    @Override
    public List<SimpleActor> getSimpleActorByMovieId(int movieId) {
        List<Actor> actors = actorMapper.getActorByMovieId(movieId);
        List<SimpleActor> simpleActors = new ArrayList<>();
        for (Actor actor: actors) {
            simpleActors.add(new SimpleActor(actor.getId(), actor.getName()));
        }
        return simpleActors;
    }

    @Override
    public List<Actor> getLikeActorWithLimit(SearchVo searchVo) {
        int pageSize = ConstantParam.DEFAULT_PAGE_SIZE;
        QueryWrapper<Actor> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        wrapper.last("limit " + (Integer.parseInt(searchVo.getPage()) - 1) * pageSize + "," + pageSize);
        return this.list(wrapper);
    }

    @Override
    public long getLikeActorCount(SearchVo searchVo) {
        QueryWrapper<Actor> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        return this.count(wrapper);
    }

    @Override
    public List<Actor> getFirstLikeActor(HintVo hintVo) {
        QueryWrapper<Actor> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", hintVo.getWords());
        wrapper.last("limit 0,10");
        return this.list(wrapper);
    }
}
