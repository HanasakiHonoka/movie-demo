package com.xzx.servie.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.MovieStateEnum;
import com.xzx.dto.MoviePreQueryDTO;
import com.xzx.entity.Movie;
import com.xzx.entity.MoviePrediction;
import com.xzx.mapper.MoviePredictionMapper;
import com.xzx.servie.IActorService;
import com.xzx.servie.IDirectorService;
import com.xzx.servie.IMoviePredictionService;
import com.xzx.servie.IScenaristService;
import com.xzx.vo.MoviePredictionPageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MoviePredictionServiceImpl
 * @Description
 * @Author XZX
 * @Date 2021/1/10 15:37
 * @Version V1.0
 **/
@Service
public class MoviePredictionServiceImpl extends ServiceImpl<MoviePredictionMapper, MoviePrediction> implements IMoviePredictionService {

    @Autowired
    private IActorService actorService;

    @Autowired
    private IDirectorService directorService;

    @Autowired
    private IScenaristService scenaristService;

    @Override
    public IPage<MoviePredictionPageVO> getMoviePredictionPage(MoviePreQueryDTO movieQueryDTO) {
        Page page = new Page<>(movieQueryDTO.getPage(), movieQueryDTO.getPageSize());
        QueryWrapper<MoviePrediction> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(movieQueryDTO.getTitle()), "title", movieQueryDTO.getTitle());
        if (movieQueryDTO.getBoxoffice() != null) {
            queryWrapper.orderBy(true, movieQueryDTO.getBoxoffice(), MoviePrediction.EXPECTED_BOXOFFICE);
        }
        if (movieQueryDTO.getReleaseTime() != null) {
            queryWrapper.orderBy(true, movieQueryDTO.getReleaseTime(), Movie.RELEASE_TIME);
        }
        if (movieQueryDTO.getState() != null) {
            queryWrapper.eq("state", movieQueryDTO.getState());
        }
        if (movieQueryDTO.getAccuracy() != null) {
            queryWrapper.last("ORDER BY ABS(predicted_boxoffice - boxoffice) / ((boxoffice + predicted_boxoffice + ABS(predicted_boxoffice - boxoffice))/2) DESC");
        }
        IPage<MoviePrediction> data = baseMapper.selectPage(page, queryWrapper);
        List<MoviePredictionPageVO> voList = new ArrayList<>();
        List<MoviePrediction> records = data.getRecords();
        for (MoviePrediction record : records) {
            MoviePredictionPageVO moviePredictionPageVO = BeanUtil.copyProperties(record, MoviePredictionPageVO.class);
            int movieId = record.getId();
            moviePredictionPageVO.setState(MovieStateEnum.getInfo(record.getState()));
            moviePredictionPageVO.setActors(actorService.getNotShownSimpleActorByMovieId(movieId));
            moviePredictionPageVO.setDirectors(directorService.getNotShownSimpleDirectorByMovieId(movieId));
            moviePredictionPageVO.setScenarists(scenaristService.getNotShownSimpleScenaristByMovieId(movieId));
            voList.add(moviePredictionPageVO);
        }
        IPage<MoviePredictionPageVO> res = new Page<>();
        res.setTotal(data.getTotal());
        res.setCurrent(data.getCurrent());
        res.setSize(data.getSize());
        res.setRecords(voList);
        return res;
    }

    @Override
    public MoviePredictionPageVO getMoviePredictionVO(Integer movieId) {
        MoviePrediction moviePrediction = baseMapper.selectById(movieId);
        MoviePredictionPageVO moviePredictionPageVO = BeanUtil.copyProperties(moviePrediction, MoviePredictionPageVO.class);
        moviePredictionPageVO.setState(MovieStateEnum.getInfo(moviePrediction.getState()));
        moviePredictionPageVO.setActors(actorService.getNotShownSimpleActorByMovieId(movieId));
        moviePredictionPageVO.setDirectors(directorService.getNotShownSimpleDirectorByMovieId(movieId));
        moviePredictionPageVO.setScenarists(scenaristService.getNotShownSimpleScenaristByMovieId(movieId));
        return moviePredictionPageVO;
    }
}