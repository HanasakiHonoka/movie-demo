package com.xzx.servie.impl;

import com.xzx.dto.SimpleDirector;
import com.xzx.entity.Director;
import com.xzx.entity.DirectorExample;
import com.xzx.entity.DirectorExample;
import com.xzx.mapper.DirectorExtendMapper;
import com.xzx.mapper.DirectorMapper;
import com.xzx.mapper.DirectorMapper;
import com.xzx.servie.DirectorService;
import com.xzx.servie.DirectorService;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private DirectorExtendMapper directorExtendMapper;

    @Override
    public List<Director> getLikeDirectorWithLimit(SearchVo searchVo) {
        DirectorExample example = new DirectorExample();
        DirectorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + searchVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds((Integer.parseInt(searchVo.getPage()) - 1) * 10,10);
        return directorMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<Director> getFirstLikeDirector(HintVo hintVo) {
        DirectorExample example = new DirectorExample();
        DirectorExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike(hintVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds(0,10);
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

    @Override
    public List<Director> getDirectors() {
        return directorMapper.selectByExample(new DirectorExample());
    }

    @Override
    public Integer updateDirector(Director director) {
        return directorMapper.updateByPrimaryKey(director);
    }

    @Override
    public Integer insertDirector(Director director) {
        return directorMapper.insert(director);
    }

    @Override
    public Integer delDirector(Integer directorId) {
        return directorMapper.deleteByPrimaryKey(directorId);
    }

    @Override
    public List<SimpleDirector> getSimpleDirectorByMovieId(Integer movieId) {
        List<Director> directors = directorExtendMapper.getDirectorByMovieId(movieId);
        List<SimpleDirector> simpleDirectors = new ArrayList<>();
        for(Director director:directors) {
            simpleDirectors.add(new SimpleDirector(director.getId(), director.getName()));
        }
        return simpleDirectors;
    }

    @Override
    public List<Director> getDirectorByName(String name) {
        DirectorExample example = new DirectorExample();
        DirectorExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        return directorMapper.selectByExample(example);
    }
}
