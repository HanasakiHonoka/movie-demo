package com.xzx.controller;


import com.xzx.dto.DirectorWithMovie;
import com.xzx.entity.Director;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "DirectorController", tags = "导演模块")
@RestController
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @Autowired
    private MovieService movieService;

    @ApiOperation("按id获取导演信息")
    @GetMapping("/director/{id}")
    public DirectorWithMovie getDirector(@PathVariable(value = "id") Integer id) {

        Director director = directorService.getDirector(id);
        DirectorWithMovie directorWithMovie = new DirectorWithMovie();
        directorWithMovie.setDirector(director);
        directorWithMovie.setAMovies(movieService.getSimpleMovieByActorId(id));
        directorWithMovie.setDMovies(movieService.getSimpleMovieByDirectorId(id));
        directorWithMovie.setSMovies(movieService.getSimpleMovieByScenaristId(id));
        return directorWithMovie;
    }

}
