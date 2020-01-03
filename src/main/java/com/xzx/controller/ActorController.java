package com.xzx.controller;

import com.xzx.dto.ActorWithMovie;
import com.xzx.entity.Actor;
import com.xzx.servie.ActorService;
import com.xzx.servie.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ActorController", tags = "演员模块")
@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MovieService movieService;

    @ApiOperation("按id获取演员信息")
    @GetMapping("/actor/{id}")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "演员ID", example = "1000525"))
    public ActorWithMovie getActor(@PathVariable(value = "id") Integer id) {

        Actor actor = actorService.getActor(id);
        ActorWithMovie actorWithMovie = new ActorWithMovie();
        actorWithMovie.setActor(actorService.getActor(id));
        actorWithMovie.setAMovies(movieService.getSimpleMovieByActorId(id));
        actorWithMovie.setDMovies(movieService.getSimpleMovieByDirectorId(id));
        actorWithMovie.setSMovies(movieService.getSimpleMovieByScenaristId(id));
        return actorWithMovie;
    }





}
