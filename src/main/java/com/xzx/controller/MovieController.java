package com.xzx.controller;

import com.xzx.dto.MovieWithActor;
import com.xzx.entity.Movie;
import com.xzx.servie.ActorService;
import com.xzx.servie.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "MovieController", tags = "电影模块")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;


    @ApiOperation("按id获取电影信息")
    @GetMapping("/movie/{id}")
    //public ResultVo<Movie> getMovie(@PathVariable String id) {
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "电影ID"))

    public MovieWithActor getMovie(@PathVariable(value = "id") String id) {
        MovieWithActor movieWithActor = new MovieWithActor();
        movieWithActor.setMovie(movieService.getMovie(Integer.parseInt(id)));
        movieWithActor.setActors(actorService.getActorByMovieId(Integer.parseInt(id)));
        return movieWithActor;
    }


}
