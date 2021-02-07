package com.xzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.dto.MoviePreQueryDTO;
import com.xzx.entity.Movie;
import com.xzx.entity.MoviePrediction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MoviePredictionMapper extends BaseMapper<MoviePrediction> {
    @Select("<script> select * from movie_prediction_since2019 mps where  </script>")
    public MoviePrediction getMoviePredictionPage(@Param("query") MoviePreQueryDTO queryDTO);

}
