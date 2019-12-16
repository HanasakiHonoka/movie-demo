package com.xzx.controller;


import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.entity.Movie;
import com.xzx.entity.Scenarist;
import com.xzx.servie.ActorService;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.servie.ScenaristService;
import com.xzx.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "SearchController", tags = "搜索模块")
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private ScenaristService scenaristService;

    @ApiOperation(value = "搜索转发")
    @GetMapping("/")
    public String getTypeAndWords(SearchVo searchVo) {
        /*if(searchVo.getType() == 1) {
            MovieListVo movieListVo = new MovieListVo();
            movieListVo.setMsg("1");
            List<Movie> moviesWithLimit = movieService.getLikeMovieWithLimit(searchVo);
            List<Movie> movies = movieService.getLikeMovie(searchVo);
            movieListVo.setMovies(moviesWithLimit);
            movieListVo.setSize(movies.size());
            return new ResultVo<MovieListVo>(movieListVo);
        }else if (searchVo.getType() == 2){

            return null;
        }else {
            return null;
        }*/
        if(searchVo.getType() == 1) {
            return "forward:/search/movies";
        } else if (searchVo.getType() == 2) {
            return "forward:/search/directors";
        } else if (searchVo.getType() == 3) {
            return "forward:/search/actors";
        } else if (searchVo.getType() == 4) {
            return "forward:/search/scenarists";
        } else {
            return null;
        }
    }
    @ApiOperation(value = "返回电影搜索结果")
    @GetMapping("/movies")
    @ResponseBody
    public MovieListVo getMovies(SearchVo searchVo) {
        MovieListVo movieListVo = new MovieListVo();
        movieListVo.setMsg("1");
        List<Movie> moviesWithLimit = movieService.getLikeMovieWithLimit(searchVo);
        List<Movie> movies = movieService.getLikeMovie(searchVo);
        movieListVo.setMovies(moviesWithLimit);
        movieListVo.setSize(movies.size());
        return movieListVo;
    }

    @ApiOperation(value = "返回导演搜索结果")
    @GetMapping("/directors")
    @ResponseBody
    public DirectorListVo getDirectors(SearchVo searchVo) {
        DirectorListVo directorListVo = new DirectorListVo();
        directorListVo.setMsg("2");
        List<Director> directorsWithLimit =directorService.getLikeDirectorWithLimit(searchVo);
        long directorSize = directorService.getLikeDirectorCount(searchVo);
        directorListVo.setDirectors(directorsWithLimit);
        directorListVo.setSize(directorSize);
        return directorListVo;
    }

    @ApiOperation(value = "返回演员搜索结果")
    @GetMapping("/actors")
    @ResponseBody
    public ActorListVo getActors(SearchVo searchVo) {
        ActorListVo actorListVo = new ActorListVo();
        actorListVo.setMsg("3");
        List<Actor> actorsWithLimit =actorService.getLikeActorWithLimit(searchVo);
        long actorSize = actorService.getLikeActorCount(searchVo);
        actorListVo.setActors(actorsWithLimit);
        actorListVo.setSize(actorSize);
        return actorListVo;
    }

    @ApiOperation(value = "返回编剧搜索结果")
    @GetMapping("/scenarists")
    @ResponseBody
    public ScenaristListVo getScenarists(SearchVo searchVo) {
        ScenaristListVo scenaristListVo = new ScenaristListVo();
        scenaristListVo.setMsg("4");
        List<Scenarist> scenaristsWithLimit =scenaristService.getLikeScenaristWithLimit(searchVo);
        long scenaristSize = scenaristService.getLikeScenaristCount(searchVo);
        scenaristListVo.setScenarists(scenaristsWithLimit);
        scenaristListVo.setSize(scenaristSize);
        return scenaristListVo;
    }

}
