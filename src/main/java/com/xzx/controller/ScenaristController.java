package com.xzx.controller;

import com.xzx.dto.ScenaristWithMovie;
import com.xzx.entity.Director;
import com.xzx.entity.Scenarist;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.servie.ScenaristService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "ScenaristController", tags = "编剧模块")
@RestController
public class ScenaristController {

    @Autowired
    private ScenaristService scenaristService;

    @Autowired
    private MovieService movieService;

    @ApiOperation("按id获取编剧信息")
    @GetMapping("/scenarist/{id}")
    public ScenaristWithMovie getScenarist(@PathVariable(value = "id") Integer id) {

        Scenarist scenarist = scenaristService.getScenarist(id);
        ScenaristWithMovie scenaristWithMovie = new ScenaristWithMovie();
        scenaristWithMovie.setScenarist(scenarist);
        scenaristWithMovie.setAMovies(movieService.getSimpleMovieByActorId(id));
        scenaristWithMovie.setDMovies(movieService.getSimpleMovieByDirectorId(id));
        scenaristWithMovie.setSMovies(movieService.getSimpleMovieByScenaristId(id));

        return scenaristWithMovie;
    }

    @ApiOperation("更新编剧信息")
    @PutMapping("/scenarist")
    public Integer updateScenarist(Scenarist scenarist) {
        return scenaristService.updateScenarist(scenarist);
    }

    @ApiOperation("添加编剧")
    @PostMapping("/scenarist")
    public Integer insertScenarist(Scenarist scenarist) {
        return scenaristService.insertScenarist(scenarist);
    }

    @ApiOperation("按id删除编剧")
    @DeleteMapping("/scenarist/{id}")
    public Integer delScenarist(@PathVariable(value = "id") Integer id) {
        return scenaristService.delScenarist(id);
    }
}
