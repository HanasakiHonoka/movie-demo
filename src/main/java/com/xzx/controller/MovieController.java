package com.xzx.controller;

import com.xzx.dto.MovieWithPeople;
import com.xzx.servie.ActorService;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.servie.ScenaristService;
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

    @Autowired
    private DirectorService directorService;

    @Autowired
    private ScenaristService scenaristService;


    @ApiOperation("按id获取电影信息")
    @GetMapping("/movie/{id}")
    //public ResultVo<Movie> getMovie(@PathVariable String id) {
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "电影ID", example = "10"))

    public MovieWithPeople getMovie(@PathVariable(value = "id") String id) {
        MovieWithPeople movieWithPeople = new MovieWithPeople();
        movieWithPeople.setMovie(movieService.getMovie(Integer.parseInt(id)));
        movieWithPeople.setActors(actorService.getSimpleActorByMovieId(Integer.parseInt(id)));
        movieWithPeople.setDirectors(directorService.getSimpleDirectorByMovieId(Integer.parseInt(id)));
        movieWithPeople.setScenarists(scenaristService.getSimpleScenaristByMovieId(Integer.parseInt(id)));
        return movieWithPeople;
    }


}
