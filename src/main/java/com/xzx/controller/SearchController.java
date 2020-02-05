package com.xzx.controller;


import com.xzx.dto.*;
import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.entity.Movie;
import com.xzx.entity.Scenarist;
import com.xzx.servie.ActorService;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.servie.ScenaristService;
import com.xzx.util.SimpleMovieUtil;
import com.xzx.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
        if (searchVo.getType() == 1) {
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
        List<MovieWithPeople> movieWithPeopleList = new ArrayList<>();
        //遍历查询到的电影结果，加入演员编剧信息
        for (Movie movie : moviesWithLimit) {
            MovieWithPeople movieWithPeople = new MovieWithPeople();
            //加入电影自身
            movieWithPeople.setMovie(movie);
            //加入演员信息
            List<SimpleActor> actors = actorService.getSimpleActorByMovieId(movie.getId());
            if (actors.size() > 5) {
                actors = actors.subList(0, 5);
            }
            movieWithPeople.setActors(actors);

            //加入导演信息
            List<SimpleDirector> directors = directorService.getSimpleDirectorByMovieId(movie.getId());
            if (directors.size() > 2) {
                directors = directors.subList(0, 2);
            }
            movieWithPeople.setDirectors(directors);

            //加入编剧信息
            List<SimpleScenarist> scenarists = scenaristService.getSimpleScenaristByMovieId(movie.getId());
            if (scenarists.size() > 3) {
                scenarists = scenarists.subList(0, 3);
            }
            movieWithPeople.setScenarists(scenarists);

            //加入结果列表
            movieWithPeopleList.add(movieWithPeople);
        }
        movieListVo.setMovies(movieWithPeopleList);
        movieListVo.setSize(movieService.getLikeMovieCount(searchVo));
        return movieListVo;
    }

    @ApiOperation(value = "返回电影名提示")
    @GetMapping("/movies/name")
    @ResponseBody
    public List<HintResVo> getMoviesName(HintVo hintVo) {
        List<HintResVo> moviesName = new ArrayList<>();
        List<Movie> moviesWithLimit = movieService.getFirstLikeMovie(hintVo);
        for (Movie movie : moviesWithLimit) {
            moviesName.add(new HintResVo(movie.getTitle()));
        }
        return moviesName;
    }

    @ApiOperation(value = "返回导演搜索结果")
    @GetMapping("/directors")
    @ResponseBody
    public DirectorListVo getDirectors(SearchVo searchVo) {
        DirectorListVo directorListVo = new DirectorListVo();
        directorListVo.setMsg("2");
        //查找到所有符合条件的导演
        List<Director> directorsWithLimit = directorService.getLikeDirectorWithLimit(searchVo);
        long directorSize = directorService.getLikeDirectorCount(searchVo);
        List<DirectorWithMovie> directorWithMovieList = new ArrayList<>();
        //遍历每个导演
        for (Director director : directorsWithLimit) {
            DirectorWithMovie directorWithMovie = new DirectorWithMovie();
            directorWithMovie.setDirector(director);
            //加入参演电影
            directorWithMovie.setAMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, director.getId(), "Actor"));
            //加入执导电影
            directorWithMovie.setDMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, director.getId(), "Director"));
            //加入编剧电影
            directorWithMovie.setSMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, director.getId(), "Scenarist"));
            directorWithMovieList.add(directorWithMovie);
        }
        directorListVo.setDirectors(directorWithMovieList);
        directorListVo.setSize(directorSize);
        return directorListVo;
    }

    @ApiOperation(value = "返回导演名提示")
    @GetMapping("/directors/name")
    @ResponseBody
    public List<HintResVo> getDirectorsName(HintVo hintVo) {
        List<HintResVo> directorsName = new ArrayList<>();
        List<Director> directorsWithLimit = directorService.getFirstLikeDirector(hintVo);
        for (Director director : directorsWithLimit) {
            directorsName.add(new HintResVo(director.getName()));
        }
        return directorsName;
    }

    @ApiOperation(value = "返回演员搜索结果")
    @GetMapping("/actors")
    @ResponseBody
    public ActorListVo getActors(SearchVo searchVo) {
        ActorListVo actorListVo = new ActorListVo();
        actorListVo.setMsg("3");
        //查询到所有符合条件的演员
        List<Actor> actorsWithLimit = actorService.getLikeActorWithLimit(searchVo);
        long actorSize = actorService.getLikeActorCount(searchVo);
        List<ActorWithMovie> actorWithMovieList = new ArrayList<>();
        //遍历每个演员
        for (Actor actor : actorsWithLimit) {
            ActorWithMovie actorWithMovie = new ActorWithMovie();
            actorWithMovie.setActor(actor);

            //加入参演电影
            actorWithMovie.setAMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, actor.getId(), "Actor"));
            //加入执导电影
            actorWithMovie.setDMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, actor.getId(), "Director"));
            //加入编剧电影
            actorWithMovie.setSMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, actor.getId(), "Scenarist"));
            actorWithMovieList.add(actorWithMovie);
        }
        actorListVo.setActors(actorWithMovieList);
        actorListVo.setSize(actorSize);
        return actorListVo;
    }

    @ApiOperation(value = "返回演员名提示")
    @GetMapping("/actors/name")
    @ResponseBody
    public List<HintResVo> getActorsName(HintVo hintVo) {
        List<HintResVo> actorsName = new ArrayList<>();
        List<Actor> actorsWithLimit = actorService.getFirstLikeActors(hintVo);
        for (Actor actor : actorsWithLimit) {
            actorsName.add(new HintResVo(actor.getName()));
        }
        return actorsName;
    }

    @ApiOperation(value = "返回编剧搜索结果")
    @GetMapping("/scenarists")
    @ResponseBody
    public ScenaristListVo getScenarists(SearchVo searchVo) {
        ScenaristListVo scenaristListVo = new ScenaristListVo();
        scenaristListVo.setMsg("4");
        List<Scenarist> scenaristsWithLimit = scenaristService.getLikeScenaristWithLimit(searchVo);
        long scenaristSize = scenaristService.getLikeScenaristCount(searchVo);
        List<ScenaristWithMovie> scenaristWithMovieList = new ArrayList<>();

        for (Scenarist scenarist : scenaristsWithLimit) {
            ScenaristWithMovie scenaristWithMovie = new ScenaristWithMovie();
            scenaristWithMovie.setScenarist(scenarist);

            //加入参演电影
            scenaristWithMovie.setAMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, scenarist.getId(), "Actor"));
            //加入执导电影
            scenaristWithMovie.setDMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, scenarist.getId(), "Director"));
            //加入编剧电影
            scenaristWithMovie.setSMovies(SimpleMovieUtil.Limit5SimpleMovie(movieService, scenarist.getId(), "Scenarist"));
            scenaristWithMovieList.add(scenaristWithMovie);
        }

        scenaristListVo.setScenarists(scenaristWithMovieList);
        scenaristListVo.setSize(scenaristSize);
        return scenaristListVo;
    }

    @ApiOperation(value = "返回编剧名提示")
    @GetMapping("/scenarists/name")
    @ResponseBody
    public List<HintResVo> getScenaristsName(HintVo hintVo) {
        List<HintResVo> scenaristsName = new ArrayList<>();
        List<Scenarist> scenaristsWithLimit = scenaristService.getFirstLikeScenarist(hintVo);
        for (Scenarist scenarist : scenaristsWithLimit) {
            scenaristsName.add(new HintResVo(scenarist.getName()));
        }
        return scenaristsName;
    }

    @ApiOperation("获得各表总数")
    @GetMapping("/count")
    @ResponseBody
    public CountVo getCount() {
        CountVo countVo = new CountVo();
        countVo.setMovieCount(movieService.getMovieCount());
        countVo.setActorCount(actorService.getActorCount());
        countVo.setDirectorCount(directorService.getDirectorCount());
        countVo.setScenaristCount(scenaristService.getScenaristCount());
        return countVo;
    }

    @ApiOperation("获得各表列名")
    @GetMapping("/fields/{type}")
    @ResponseBody
    public List<String> getClassFields(@PathVariable(value = "type") Integer type) throws ClassNotFoundException {
        Class<?> aClass = null;
        if (type == 1) {
            //movie
            aClass = Class.forName("com.xzx.entity.Movie");
        } else if (type == 2) {
            //director
            aClass = Class.forName("com.xzx.entity.Director");
        } else if (type == 3) {
            //actor
            aClass = Class.forName("com.xzx.entity.Actor");
        } else if (type == 4) {
            //scenarist
            aClass = Class.forName("com.xzx.entity.Scenarist");
        }
        List<String> res = new ArrayList<>();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //System.out.println(field.getName());
            res.add(field.getName());
        }

        return res;
    }

}
