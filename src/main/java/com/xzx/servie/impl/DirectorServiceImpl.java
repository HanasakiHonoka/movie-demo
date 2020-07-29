package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.SimpleDirector;
import com.xzx.entity.Director;
import com.xzx.mapper.DirectorMapper;
import com.xzx.servie.IDirectorService;
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
public class DirectorServiceImpl extends ServiceImpl<DirectorMapper, Director> implements IDirectorService {

    @Autowired
    DirectorMapper directorMapper;

    @Override
    public List<SimpleDirector> getSimpleDirectorByMovieId(int movieId) {
        List<Director> directors = directorMapper.getDirectorByMovieId(movieId);
        List<SimpleDirector> simpleDirectors = new ArrayList<>();
        for(Director director:directors) {
            simpleDirectors.add(new SimpleDirector(director.getId(), director.getName()));
        }
        return simpleDirectors;
    }

    @Override
    public List<Director> getLikeDirectorWithLimit(SearchVo searchVo) {
        int pageSize = searchVo.getPageSize();
        QueryWrapper<Director> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        wrapper.last("limit " + (Integer.parseInt(searchVo.getPage()) - 1) * pageSize + "," + pageSize);
        return this.list(wrapper);
    }

    @Override
    public long getLikeDirectorCount(SearchVo searchVo) {
        QueryWrapper<Director> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        return this.count(wrapper);
    }

    @Override
    public List<Director> getFirstLikeDirector(HintVo hintVo) {
        QueryWrapper<Director> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", hintVo.getWords());
        wrapper.last("limit 0,10");
        return this.list(wrapper);
    }
}
