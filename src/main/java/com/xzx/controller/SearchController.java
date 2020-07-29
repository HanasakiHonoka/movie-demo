package com.xzx.controller;


import com.xzx.constant.SearchTypeEnum;
import com.xzx.dto.*;
import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.entity.Movie;
import com.xzx.entity.Scenarist;
import com.xzx.servie.IActorService;
import com.xzx.servie.IDirectorService;
import com.xzx.servie.IMovieService;
import com.xzx.servie.IScenaristService;
import com.xzx.util.SimpleMovieUtil;
import com.xzx.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(value = "SearchController", tags = "搜索模块")
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private IMovieService movieService;
    @Autowired
    private IActorService actorService;
    @Autowired
    private IDirectorService directorService;
    @Autowired
    private IScenaristService scenaristService;

    @ApiOperation(value = "搜索转发")
    @GetMapping("/")
    public String getTypeAndWords(SearchVo searchVo) {
        searchVo.setDefaultValue();
        log.info(searchVo.toString());
        if (searchVo.getType().equals(SearchTypeEnum.MOVIE.getValue())) {
            return "forward:/search/movies";
        } else if (searchVo.getType().equals(SearchTypeEnum.DIRECTOR.getValue())) {
            return "forward:/search/directors";
        } else if (searchVo.getType().equals(SearchTypeEnum.ACTOR.getValue())) {
            return "forward:/search/actors";
        } else if (searchVo.getType().equals(SearchTypeEnum.SCENARIST.getValue())) {
            return "forward:/search/scenarists";
        } else {
            return null;
        }
    }

    @ApiOperation(value = "返回电影搜索结果")
    @GetMapping("/movies")
    @ResponseBody
    public MovieListVo getMovies(SearchVo searchVo) {
        searchVo.setDefaultValue();
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
            if (actors.size() > searchVo.getActorNum()) {
                actors = actors.subList(0, searchVo.getActorNum());
            }
            movieWithPeople.setActors(actors);

            //加入导演信息
            List<SimpleDirector> directors = directorService.getSimpleDirectorByMovieId(movie.getId());
            if (directors.size() > searchVo.getDirectorNum()) {
                directors = directors.subList(0, searchVo.getDirectorNum());
            }
            movieWithPeople.setDirectors(directors);

            //加入编剧信息
            List<SimpleScenarist> scenarists = scenaristService.getSimpleScenaristByMovieId(movie.getId());
            if (scenarists.size() > searchVo.getScenaristNum()) {
                scenarists = scenarists.subList(0, searchVo.getScenaristNum());
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
            moviesName.add(new HintResVo(movie.getId(), movie.getTitle()));
        }
        return moviesName;
    }

    @ApiOperation(value = "返回导演搜索结果")
    @GetMapping("/directors")
    @ResponseBody
    public DirectorListVo getDirectors(SearchVo searchVo) {
        searchVo.setDefaultValue();
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
            directorWithMovie.setAMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, director.getId(), "Actor", searchVo.getActorNum()));
            //加入执导电影
            directorWithMovie.setDMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, director.getId(), "Director", searchVo.getDirectorNum()));
            //加入编剧电影
            directorWithMovie.setSMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, director.getId(), "Scenarist", searchVo.getScenaristNum()));
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
            directorsName.add(new HintResVo(director.getId(), director.getName()));
        }
        return directorsName;
    }

    @ApiOperation(value = "返回演员搜索结果")
    @GetMapping("/actors")
    @ResponseBody
    public ActorListVo getActors(SearchVo searchVo) {
        searchVo.setDefaultValue();
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
            actorWithMovie.setAMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, actor.getId(), "Actor", searchVo.getActorNum()));
            //加入执导电影
            actorWithMovie.setDMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, actor.getId(), "Director", searchVo.getDirectorNum()));
            //加入编剧电影
            actorWithMovie.setSMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, actor.getId(), "Scenarist", searchVo.getDirectorNum()));
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
        List<Actor> actorsWithLimit = actorService.getFirstLikeActor(hintVo);
        for (Actor actor : actorsWithLimit) {
            actorsName.add(new HintResVo(actor.getId(), actor.getName()));
        }
        return actorsName;
    }

    @ApiOperation(value = "返回编剧搜索结果")
    @GetMapping("/scenarists")
    @ResponseBody
    public ScenaristListVo getScenarists(SearchVo searchVo) {
        searchVo.setDefaultValue();
        ScenaristListVo scenaristListVo = new ScenaristListVo();
        scenaristListVo.setMsg("4");
        List<Scenarist> scenaristsWithLimit = scenaristService.getLikeScenaristWithLimit(searchVo);
        long scenaristSize = scenaristService.getLikeScenaristCount(searchVo);
        List<ScenaristWithMovie> scenaristWithMovieList = new ArrayList<>();

        for (Scenarist scenarist : scenaristsWithLimit) {
            ScenaristWithMovie scenaristWithMovie = new ScenaristWithMovie();
            scenaristWithMovie.setScenarist(scenarist);

            //加入参演电影
            scenaristWithMovie.setAMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, scenarist.getId(), "Actor", searchVo.getActorNum()));
            //加入执导电影
            scenaristWithMovie.setDMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, scenarist.getId(), "Director", searchVo.getDirectorNum()));
            //加入编剧电影
            scenaristWithMovie.setSMovies(SimpleMovieUtil.getSimpleMovieByPersonId(movieService, scenarist.getId(), "Scenarist", searchVo.getScenaristNum()));
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
            scenaristsName.add(new HintResVo(scenarist.getId(), scenarist.getName()));
        }
        return scenaristsName;
    }

    @ApiOperation("获得各表总数")
    @GetMapping("/count")
    @ResponseBody
    public CountVo getCount() {
        CountVo countVo = new CountVo();
        countVo.setMovieCount(movieService.count());
        countVo.setActorCount(actorService.count());
        countVo.setDirectorCount(directorService.count());
        countVo.setScenaristCount(scenaristService.count());
        return countVo;
    }

    @ApiOperation("获得各表列名")
    @GetMapping("/fields/{type}")
    @ResponseBody
    public List<String> getClassFields(@PathVariable(value = "type") Integer type) throws ClassNotFoundException {
        Class<?> aClass = null;
        if (type.equals(SearchTypeEnum.MOVIE.getValue())) {
            //movie
            aClass = Class.forName("com.xzx.entity.Movie");
        } else if (type.equals(SearchTypeEnum.DIRECTOR.getValue())) {
            //director
            aClass = Class.forName("com.xzx.entity.Director");
        } else if (type.equals(SearchTypeEnum.ACTOR.getValue())) {
            //actor
            aClass = Class.forName("com.xzx.entity.Actor");
        } else if (type.equals(SearchTypeEnum.SCENARIST.getValue())) {
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
