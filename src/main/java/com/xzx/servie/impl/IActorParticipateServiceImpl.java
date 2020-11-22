package com.xzx.servie.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.entity.Actor;
import com.xzx.entity.ActorParticipate;
import com.xzx.entity.Dictionary;
import com.xzx.mapper.ActorParticipateMapper;
import com.xzx.servie.IActorParticipateService;
import com.xzx.servie.IActorService;
import com.xzx.servie.IDictionaryService;
import com.xzx.vo.ActorParticipateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    IActorService actorService;

    @Autowired
    IDictionaryService dictionaryService;

    @Override
    public List<ActorParticipate> getByMovieId(Integer movieId) {
        QueryWrapper<ActorParticipate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ActorParticipate.MOVIE_ID, movieId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<ActorParticipateVO> getVOByMovieId(Integer movieId) {
        List<ActorParticipate> actorParticipateList = this.getByMovieId(movieId);
        List<ActorParticipateVO> voList = new ArrayList<>();
        for (ActorParticipate actorParticipate : actorParticipateList) {
            voList.add(this.getVOByRaw(actorParticipate));
        }
        return voList;
    }

    @Override
    public List<ActorParticipate> getByActorId(Integer ActorId) {
        QueryWrapper<ActorParticipate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ActorParticipate.ACTOR_ID, ActorId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<ActorParticipateVO> getVOByActorId(Integer ActorId) {
        List<ActorParticipate> actorParticipateList = this.getByActorId(ActorId);
        List<ActorParticipateVO> voList = new ArrayList<>();
        for (ActorParticipate actorParticipate : actorParticipateList) {
            voList.add(this.getVOByRaw(actorParticipate));
        }
        return voList;
    }

    @Override
    public boolean update(ActorParticipate actorParticipate) {
        QueryWrapper<ActorParticipate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ActorParticipate.ACTOR_ID, actorParticipate.getActorId());
        queryWrapper.eq(ActorParticipate.MOVIE_ID, actorParticipate.getMovieId());
        int update = baseMapper.update(actorParticipate, queryWrapper);
        return update > 0;
    }

    public ActorParticipateVO getVOByRaw(ActorParticipate actorParticipate) {
        ActorParticipateVO actorParticipateVO = BeanUtil.copyProperties(actorParticipate, ActorParticipateVO.class);
        Actor actor = actorService.getById(actorParticipate.getActorId());
        if (actor != null) {
            actorParticipateVO.setActorName(actor.getName());
        }
        //角色职业
        actorParticipateVO.setRoleOccupationName(dictionaryService.getNameByDicNo(actorParticipate.getRoleOccupation()));
        //人设
        actorParticipateVO.setRoleSetName(dictionaryService.getNameByDicNo(actorParticipate.getRoleSet()));
        //年龄段
        actorParticipateVO.setRoleAgeName(dictionaryService.getNameByDicNo(actorParticipate.getRoleAge()));
        //性别
        actorParticipateVO.setRoleGenderName(dictionaryService.getNameByDicNo(actorParticipate.getRoleGender()));
        //性格
        actorParticipateVO.setRoleCharacterName(dictionaryService.getNameByDicNo(actorParticipate.getRoleCharacter()));

        return actorParticipateVO;
    }

}
