package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.entity.ActorParticipate;
import com.xzx.mapper.ActorParticipateMapper;
import com.xzx.servie.IActorParticipateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname IActorParticipateServiceImpl
 * @Description
 * @Date 2020/11/6 22:19
 * @Author XZX
 * @Version 1.0
 */
@Service
public class IActorParticipateServiceImpl extends ServiceImpl<ActorParticipateMapper, ActorParticipate> implements IActorParticipateService {


    @Override
    public List<ActorParticipate> getByMovieId(Integer movieId) {
        QueryWrapper<ActorParticipate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ActorParticipate.MOVIE_ID, movieId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<ActorParticipate> getByActorId(Integer ActorId) {
        QueryWrapper<ActorParticipate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ActorParticipate.ACTOR_ID, ActorId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean update(ActorParticipate actorParticipate) {
        QueryWrapper<ActorParticipate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ActorParticipate.ACTOR_ID, actorParticipate.getActorId());
        queryWrapper.eq(ActorParticipate.MOVIE_ID, actorParticipate.getMovieId());
        int update = baseMapper.update(actorParticipate, queryWrapper);
        return update > 0;
    }

}
