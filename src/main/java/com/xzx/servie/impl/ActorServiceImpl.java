package com.xzx.servie.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.ActorQueryDTO;
import com.xzx.dto.PeopleWithBox;
import com.xzx.dto.SimpleActor;
import com.xzx.entity.Actor;
import com.xzx.mapper.ActorMapper;
import com.xzx.servie.IActorService;
import com.xzx.vo.HintVo;
import com.xzx.vo.MgtActorPageVO;
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
    public IPage<MgtActorPageVO> getActorPage(ActorQueryDTO actorQueryDTO) {
        Page<Actor> page = new Page<>(actorQueryDTO.getPage(), actorQueryDTO.getPageSize());
        QueryWrapper<Actor> queryWrapper = new QueryWrapper<>();
        Page<Actor> data = actorMapper.selectPage(page, queryWrapper);
        List<Actor> actorList = data.getRecords();
        List<MgtActorPageVO> voList = new ArrayList<>();
        for (Actor actor : actorList) {
            MgtActorPageVO mgtActorPageVO = BeanUtil.copyProperties(actor, MgtActorPageVO.class);
            voList.add(mgtActorPageVO);
        }
        IPage<MgtActorPageVO> res = new Page<>();
        res.setTotal(data.getTotal());
        res.setCurrent(data.getCurrent());
        res.setSize(data.getSize());
        res.setRecords(voList);
        return res;
    }

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
        int pageSize = searchVo.getPageSize();
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

    @Override
    public List<PeopleWithBox> getTopActorWithBox() {
        return actorMapper.getTopActorWithBox();
    }
}
