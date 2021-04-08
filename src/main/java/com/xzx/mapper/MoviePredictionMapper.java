package com.xzx.mapper;

import cn.hutool.Hutool;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.dto.MoviePreQueryDTO;
import com.xzx.entity.Movie;
import com.xzx.entity.MoviePrediction;
import com.xzx.vo.MoviePredictionPageVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MoviePredictionMapper extends BaseMapper<MoviePrediction> {
//    @Select("<script> select * from movie_prediction_since2019 mps where  </script>")
//    public MoviePrediction getMoviePredictionPage(@Param("query") MoviePreQueryDTO queryDTO);
//
////    @Select("select * from movie_prediction_since2019 mps " +
////            "where #{date1} <= mps.release_time " +
////            "and mps.release_time <= #{date2};")
//    @Select("<script> select * from movie_prediction_since2019 mps where  </script>")
//    public List<MoviePredictionPageVO> scheduleSelection(@Param("date1")String date1, @Param("date2")String date2);
}

