package com.xzx.controller;

import com.xzx.entity.Movie;
import com.xzx.servie.MovieService;
import com.xzx.vo.MovieListVo;
import com.xzx.vo.ResultVo;
import com.xzx.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "MovieController", tags = "电影接口")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    //@ResponseBody
    //@RequestMapping("/movie/{id}")
    //public String getMovieInfo(@PathVariable(value = "id") Integer id) {
    //    Movie movie = movieService.findById(id);
    //    if(movie != null) {
    //        System.out.println(movie.getName());
    //        return "success";
    //    }else {
    //        return "fail";
    //    }
    //
    //}

    @ApiOperation(value = "搜索结果")
    @GetMapping("/search")
    public MovieListVo getMovies(SearchVo searchVo) {
        MovieListVo movieListVo = new MovieListVo();
        if(searchVo.getType() == 1) {
            movieListVo.setMsg("1");
            List<Movie> moviesWithLimit = movieService.getLikeMovieWithLimit(searchVo);
            List<Movie> movies = movieService.getLikeMovie(searchVo);
            movieListVo.setMovies(moviesWithLimit);
            movieListVo.setSize(movies.size());
        }else {

            movieListVo.setMsg("0");
        }

        return movieListVo;
    }

    @ApiOperation("按id获取电影信息")
    @GetMapping("/movie/{id}")
    //public ResultVo<Movie> getMovie(@PathVariable String id) {
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "电影ID"))

    public Movie getMovie(@PathVariable(value = "id") String id) {
        //return new ResultVo<Movie>(movieService.getMovie(Integer.parseInt(id)));
        return movieService.getMovie(Integer.parseInt(id));
    }

}
