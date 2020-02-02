package com.xzx.controller;

import com.xzx.dto.MovieWithPeople;
import com.xzx.entity.Movie;
import com.xzx.servie.ActorService;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.servie.ScenaristService;
import com.xzx.vo.MgtMovieListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @ApiOperation("更新电影信息")
    @PutMapping("/movie")
    public Integer updateMovie(Movie movie) {
        return movieService.updateMovie(movie);
    }

    @ApiOperation("添加电影")
    @PostMapping("/movie")
    public Integer insertMovie(Movie movie) {
        return movieService.insertMovie(movie);
    }

    //@ApiOperation("批量添加电影")
    //@PostMapping("/movies")
    //public Integer insertMultiMovie(List<Movie> movies) {
    //    return null;
    //}

    @ApiOperation("按id删除电影")
    @DeleteMapping("/movie/{id}")
    public Integer delMovie(@PathVariable(value = "id") String id) {
        return movieService.delMovie(Integer.parseInt(id));
    }

    @ApiOperation("获取所有电影")
    @GetMapping("/movies")
    public MgtMovieListVo getMovies() {
        List<MovieWithPeople> movieWithPeoples = new ArrayList<>();
        List<Movie> movies = movieService.getMovies();
        for (Movie movie:movies) {
            MovieWithPeople movieWithPeople = new MovieWithPeople();
            movieWithPeople.setMovie(movie);
            movieWithPeoples.add(movieWithPeople);
        }
        return new MgtMovieListVo(movieWithPeoples);
    }


}
