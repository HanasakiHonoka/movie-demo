package com.xzx.servie.impl;

import com.xzx.entity.Director;
import com.xzx.entity.DirectorExample;
import com.xzx.entity.DirectorExample;
import com.xzx.mapper.DirectorMapper;
import com.xzx.mapper.DirectorMapper;
import com.xzx.servie.DirectorService;
import com.xzx.servie.DirectorService;
import com.xzx.vo.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorMapper directorMapper;

    @Override
    public List<Director> getLikeDirectorWithLimit(SearchVo searchVo) {
        DirectorExample example = new DirectorExample();
        DirectorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds((Integer.parseInt(searchVo.getPage()) - 1) * 10,10);
        return directorMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public long getLikeDirectorCount(SearchVo searchVo) {
        DirectorExample example = new DirectorExample();
        DirectorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        return directorMapper.countByExample(example);
    }

    @Override
    public Director getDirector(Integer id) {
        return directorMapper.selectByPrimaryKey(id);
    }
}
