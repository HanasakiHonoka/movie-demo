package com.xzx.servie;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.MoviePreQueryDTO;
import com.xzx.entity.MoviePrediction;
import com.xzx.vo.MoviePredictionPageVO;

public interface IMoviePredictionService extends IService<MoviePrediction> {
    IPage<MoviePredictionPageVO> getMoviePredictionPage(MoviePreQueryDTO movieQueryDTO);
    MoviePredictionPageVO getMoviePredictionVO(Integer movieId);
}
