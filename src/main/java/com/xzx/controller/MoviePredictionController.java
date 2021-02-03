package com.xzx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xzx.dto.MoviePreQueryDTO;
import com.xzx.dto.MovieQueryDTO;
import com.xzx.entity.MoviePrediction;
import com.xzx.dto.MoviePreQueryDTO;
import com.xzx.servie.IMoviePredictionService;
import com.xzx.vo.MoviePredictionPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName MoviePredictionController
 * @Description
 * @Author XZX
 * @Date 2021/1/10 15:51
 * @Version V1.0
 **/
@Slf4j
@Api(value = "MoviePredictionController", tags = "未上映电影模块")
@RestController
public class MoviePredictionController {

    @Autowired
    IMoviePredictionService moviePredictionService;

    @ApiOperation("分页获取所有电影")
    @GetMapping("/moviePredictionPage")
    public IPage<MoviePredictionPageVO> getMoviePredictionPage(MoviePreQueryDTO movieQueryDTO) {
        return moviePredictionService.getMoviePredictionPage(movieQueryDTO);
    }

    @ApiOperation("按id获取电影")
    @GetMapping("/moviePrediction/{id}")
    public MoviePredictionPageVO getMoviePrediction(@PathVariable(value = "id") Integer movieId) {
        HashMap<String,Integer> m;

        return moviePredictionService.getMoviePredictionVO(movieId);
    }


}
