package com.xzx.servie.impl;

import com.xzx.entity.Scenarist;
import com.xzx.entity.ScenaristExample;
import com.xzx.mapper.ScenaristMapper;
import com.xzx.servie.ScenaristService;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenaristServiceImpl implements ScenaristService {

    @Autowired
    private ScenaristMapper scenaristMapper;

    @Override
    public List<Scenarist> getLikeScenaristWithLimit(SearchVo searchVo) {
        ScenaristExample example = new ScenaristExample();
        ScenaristExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds((Integer.parseInt(searchVo.getPage()) - 1) * 10,10);
        return scenaristMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<Scenarist> getFirstLikeScenarist(HintVo hintVo) {
        ScenaristExample example = new ScenaristExample();
        ScenaristExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike(hintVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds(0,10);
        return scenaristMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public long getLikeScenaristCount(SearchVo searchVo) {
        ScenaristExample example = new ScenaristExample();
        ScenaristExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        return scenaristMapper.countByExample(example);
    }

    @Override
    public Scenarist getScenarist(Integer id) {
        return scenaristMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Scenarist> getScenaristByName(String name) {
        ScenaristExample example = new ScenaristExample();
        ScenaristExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        return scenaristMapper.selectByExample(example);
    }
}
