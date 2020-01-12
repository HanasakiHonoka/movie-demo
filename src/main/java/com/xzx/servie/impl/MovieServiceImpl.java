package com.xzx.servie.impl;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Movie;
import com.xzx.entity.MovieExample;
import com.xzx.mapper.MovieExtendMapper;
import com.xzx.mapper.MovieMapper;
import com.xzx.servie.MovieService;
import com.xzx.util.SimpleMovieUtil;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieExtendMapper movieExtendMapper;

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
    public List<Movie> getFirstLikeMovie(HintVo hintVo) {
        MovieExample example = new MovieExample();
        MovieExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike(hintVo.getWords() + "%");
        RowBounds rowBounds = new RowBounds(0,10);
        return movieMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public long getLikeMovieCount(SearchVo searchVo) {

        MovieExample example = new MovieExample();
        MovieExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%" + searchVo.getWords() + "%");
        return movieMapper.countByExample(example);
    }

    public Movie getMovie(Integer id) {

        return movieMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SimpleMovie> getSimpleMovieByActorId(Integer actorId) {
        List<Movie> movies = movieExtendMapper.getMovieByActorId(actorId);
        List<SimpleMovie> simpleMovies = SimpleMovieUtil.movieToSimpleMovie(movies);
        return simpleMovies;
    }

    @Override
    public List<SimpleMovie> getSimpleMovieByDirectorId(Integer directorId) {
        List<Movie> movies = movieExtendMapper.getMovieByDirectorId(directorId);
        List<SimpleMovie> simpleMovies = SimpleMovieUtil.movieToSimpleMovie(movies);
        return simpleMovies;
    }

    @Override
    public List<SimpleMovie> getSimpleMovieByScenaristId(Integer scenaristId) {
        List<Movie> movies = movieExtendMapper.getMovieByScenaristId(scenaristId);
        List<SimpleMovie> simpleMovies = SimpleMovieUtil.movieToSimpleMovie(movies);
        return simpleMovies;
    }

}
