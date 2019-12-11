package com.xzx.servie.impl;

import com.xzx.entity.Movie;
import com.xzx.entity.MovieExample;
import com.xzx.mapper.MovieMapper;
import com.xzx.servie.MovieService;
import com.xzx.vo.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> getLikeMovieWithLimit(SearchVo searchVo) {
        MovieExample example = new MovieExample();
        MovieExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%" + searchVo.getWords() + "%");
        //System.out.println(str);
        RowBounds rowBounds = new RowBounds((Integer.parseInt(searchVo.getPage()) - 1) * 10,10);
        return movieMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<Movie> getLikeMovie(SearchVo searchVo) {

        MovieExample example = new MovieExample();
        MovieExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%" + searchVo.getWords() + "%");
        return movieMapper.selectByExample(example);
    }

    public Movie getMovie(Integer id) {

        return movieMapper.selectByPrimaryKey(id);
    }

}
