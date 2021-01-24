package com.xzx.servie.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.MovieTypeEnum;
import com.xzx.dto.DataListQueryDTO;
import com.xzx.dto.MovieQueryDTO;
import com.xzx.dto.SimpleMovie;
import com.xzx.dto.YearBoxOffice;
import com.xzx.entity.Movie;
import com.xzx.mapper.MovieMapper;
import com.xzx.servie.IMovieService;
import com.xzx.util.SimpleMovieUtil;
import com.xzx.vo.HintVo;
import com.xzx.vo.MgtMoviePageVO;
import com.xzx.vo.MovieDataListVO;
import com.xzx.vo.SearchVo;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
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
    public IPage<MgtMoviePageVO> getMoviePage(MovieQueryDTO movieQueryDTO) {
        Page page = new Page<>(movieQueryDTO.getPage(), movieQueryDTO.getPageSize());
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        if (movieQueryDTO.getBoxoffice() != null) {
            queryWrapper.orderBy(true, movieQueryDTO.getBoxoffice(), Movie.BOXOFFICE);
        }
        if (movieQueryDTO.getReleaseTime() != null) {
            queryWrapper.orderBy(true, movieQueryDTO.getReleaseTime(), Movie.RELEASE_TIME);
        }
        queryWrapper.like(StringUtils.isNotBlank(movieQueryDTO.getType()), Movie.TYPE, movieQueryDTO.getType());
        IPage<Movie> data = baseMapper.selectPage(page, queryWrapper);
        List<Movie> movieList = data.getRecords();
        List<MgtMoviePageVO> voList = new ArrayList<>();
        for (Movie movie : movieList) {
            MgtMoviePageVO moviePageVO = BeanUtil.copyProperties(movie, MgtMoviePageVO.class);
            voList.add(moviePageVO);
        }
        IPage<MgtMoviePageVO> res = new Page<>();
        res.setTotal(data.getTotal());
        res.setCurrent(data.getCurrent());
        res.setSize(data.getSize());
        res.setRecords(voList);
        return res;
    }

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
        int pageSize = searchVo.getPageSize();
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        if (searchVo.getBoxoffice() != null) {
            wrapper.orderBy(true, searchVo.getBoxoffice(), Movie.BOXOFFICE);
        }
        if (searchVo.getReleaseTime() != null) {
            wrapper.orderBy(true, searchVo.getReleaseTime(), Movie.RELEASE_TIME);
        }
        if (searchVo.getDoubanRating() != null) {
            wrapper.orderBy(true, searchVo.getDoubanRating(), Movie.DOUBAN_RATING);
        }
        wrapper.like(StringUtils.isNotBlank(searchVo.getWords()), "title", searchVo.getWords());
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

    @Override
    public List<SimpleMovie> getTopMovie(String column) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc(column);
        List<Movie> movieList = this.list(wrapper);

        return SimpleMovieUtil.movieToSimpleMovie(movieList, 10);
    }

    @Override
    public Integer getMovieCountByType(String type) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.like("type", type);
        return this.count(wrapper);
    }

    @Override
    public List<SimpleMovie> getYearBoxTopMovie(String year) {
        List<Movie> movieList = movieMapper.getTopBoxYearMovie(year);

        return SimpleMovieUtil.movieToSimpleMovie(movieList);
    }

    @Override
    public Double getMovieBoxByYear(Integer year) {
        String year1 = year + "-01-01";
        String year2 = year + "-12-31";
        return movieMapper.getBoxByYear(year1, year2);
    }

    @Override
    public List<YearBoxOffice> getBoxAllYear() {
        List<YearBoxOffice> yearBoxOfficeList = movieMapper.getBoxAllYear();
        yearBoxOfficeList.removeIf(yearBoxOffice -> yearBoxOffice.getBoxoffice() < 1000.0);
        return yearBoxOfficeList;
    }

    @Override
    public List<MovieDataListVO> getMovieDataListVO(DataListQueryDTO dataListQueryDTO) {
        String type = dataListQueryDTO.getType();
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(type), "type", type);
        if (dataListQueryDTO.getBoxoffice() != null) {
            wrapper.orderBy(true, dataListQueryDTO.getBoxoffice(), Movie.BOXOFFICE);
        }
        if (dataListQueryDTO.getReleaseTime() != null) {
            wrapper.orderBy(true, dataListQueryDTO.getReleaseTime(), Movie.RELEASE_TIME);
        }
        if (dataListQueryDTO.getDoubanRating() != null) {
            wrapper.orderBy(true, dataListQueryDTO.getDoubanRating(), Movie.DOUBAN_RATING);
        }
        wrapper.ge(StringUtils.isNotBlank(dataListQueryDTO.getYear()), "release_time", LocalDate.ofYearDay(Integer.parseInt(dataListQueryDTO.getYear()), 1));
        wrapper.le(StringUtils.isNotBlank(dataListQueryDTO.getYear()), "release_time", LocalDate.ofYearDay(Integer.parseInt(dataListQueryDTO.getYear()), 365));
        wrapper.last("limit 10");
        List<Movie> movies = baseMapper.selectList(wrapper);
        List<MovieDataListVO> res = new ArrayList<>();
        for (Movie movie : movies) {
            MovieDataListVO movieDataListVO = BeanUtil.copyProperties(movie, MovieDataListVO.class);
            res.add(movieDataListVO);
        }
        return res;
    }


}
