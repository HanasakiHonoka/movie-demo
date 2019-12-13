package com.xzx.servie.impl;

import com.xzx.entity.Actor;
import com.xzx.entity.ActorExample;
import com.xzx.mapper.ActorMapper;
import com.xzx.servie.ActorService;
import com.xzx.vo.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public List<Actor> getLikeActorWithLimit(SearchVo searchVo) {
        ActorExample example = new ActorExample();
        ActorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds((Integer.parseInt(searchVo.getPage()) - 1) * 10,10);
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
}
