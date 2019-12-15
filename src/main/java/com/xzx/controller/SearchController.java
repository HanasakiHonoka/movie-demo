package com.xzx.controller;


import com.xzx.entity.Movie;
import com.xzx.servie.MovieService;
import com.xzx.vo.MovieListVo;
import com.xzx.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "SearchController", tags = "搜索模块")
@RestController
public class SearchController {

    @Autowired
    private MovieService movieService;

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
        }else if (searchVo.getType() == 2){

            movieListVo.setMsg("0");
        }

        return movieListVo;
    }

}
