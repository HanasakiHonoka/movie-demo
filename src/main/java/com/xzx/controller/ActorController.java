package com.xzx.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.dto.ActorWithMovie;
import com.xzx.entity.Actor;
import com.xzx.entity.Movie;
import com.xzx.servie.ActorService;
import com.xzx.servie.MovieService;
import com.xzx.vo.MgtActorListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation("更新演员信息")
    @PutMapping("/actor")
    public Integer updateActor(Actor actor) {
        return actorService.updateActor(actor);
    }

    @ApiOperation("添加演员")
    @PostMapping("/actor")
    public Integer insertActor(Actor actor) {
        return actorService.insertActor(actor);
    }

    @ApiOperation("按id删除演员")
    @DeleteMapping("/actor/{id}")
    public  Integer delActor(@PathVariable(value = "id") Integer id) {
        return actorService.delActor(id);
    }


    @ApiOperation("获取所有演员")
    @GetMapping("/actors")
    public MgtActorListVo getActors() {
        List<ActorWithMovie> actorWithMovies = new ArrayList<>();

        List<Actor> actors = actorService.getActors();

        for (Actor actor: actors) {
            ActorWithMovie actorWithMovie = new ActorWithMovie();
            actorWithMovie.setActor(actor);
            actorWithMovies.add(actorWithMovie);
        }

        return new MgtActorListVo(actorWithMovies);

    }

    @ApiOperation("csv文件导入演员数据")
    @PostMapping("/actor/csvInsert")
    public Integer insertActorByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), "utf-8");
        HeaderColumnNameMappingStrategy<Actor> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Actor.class);
        Integer res = 0;
        CsvToBean<Actor> build = new CsvToBeanBuilder<Actor>(in).withMappingStrategy(mapper).build();
        List<Actor> actorList = build.parse();
        try {
            res = actorService.insertMulti(actorList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res = 0;
        } finally {
            return res;
        }
    }

    //@ApiOperation("获得演员表总数")
    //@GetMapping("/actor/count")
    //public long getActorCount() {
    //    return actorService.getActorCount();
    //}


}
