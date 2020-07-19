package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Movie;
import com.xzx.mapper.MovieMapper;
import com.xzx.servie.IMovieService;
import com.xzx.util.SimpleMovieUtil;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    @Autowired
    MovieMapper movieMapper;


    @Override
    public List<SimpleMovie> getSimpleMovieByActorId(Integer actorId) {
        List<Movie> movies = movieMapper.getMovieByActorId(actorId);
        List<SimpleMovie> simpleMovies = SimpleMovieUtil.movieToSimpleMovie(movies);
        return simpleMovies;
    }

    @Override
    public List<SimpleMovie> getSimpleMovieByDirectorId(Integer directorId) {
        List<Movie> movies = movieMapper.getMovieByDirectorId(directorId);
        List<SimpleMovie> simpleMovies = SimpleMovieUtil.movieToSimpleMovie(movies);
        return simpleMovies;
    }

    @Override
    public List<SimpleMovie> getSimpleMovieByScenaristId(Integer scenaristId) {
        List<Movie> movies = movieMapper.getMovieByScenaristId(scenaristId);
        List<SimpleMovie> simpleMovies = SimpleMovieUtil.movieToSimpleMovie(movies);
        return simpleMovies;
    }

    @Override
    public List<Movie> getLikeMovieWithLimit(SearchVo searchVo) {
        int pageSize = ConstantParam.DEFAULT_PAGE_SIZE;
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.like("title", searchVo.getWords());
        wrapper.last("limit " + (Integer.parseInt(searchVo.getPage()) - 1) * pageSize + "," + pageSize);

        return this.list(wrapper);
    }

    @Override
    public long getLikeMovieCount(SearchVo searchVo) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.like("title", searchVo.getWords());
        return this.count(wrapper);
    }

    @Override
    public List<Movie> getFirstLikeMovie(HintVo hintVo) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.likeRight("title", hintVo.getWords());
        wrapper.last("limit 0,10");
        return this.list(wrapper);
    }

}
