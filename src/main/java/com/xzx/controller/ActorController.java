package com.xzx.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.dto.ActorWithMovie;
import com.xzx.dto.PeopleWithBox;
import com.xzx.entity.Actor;
import com.xzx.servie.IActorService;
import com.xzx.servie.IMovieService;
import com.xzx.vo.MgtActorListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(value = "ActorController", tags = "演员模块")
@RestController
public class ActorController {

    @Autowired
    private IActorService actorService;

    @Autowired
    private IMovieService movieService;

    @ApiOperation("按id获取演员信息")
    @GetMapping("/actor/{id}")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "演员ID", example = "1000525"))
    public ActorWithMovie getActor(@PathVariable(value = "id") Integer id) {

        Actor actor = actorService.getById(id);
        ActorWithMovie actorWithMovie = new ActorWithMovie();
        actorWithMovie.setActor(actor);
        actorWithMovie.setAMovies(movieService.getSimpleMovieByActorId(id));
        actorWithMovie.setDMovies(movieService.getSimpleMovieByDirectorId(id));
        actorWithMovie.setSMovies(movieService.getSimpleMovieByScenaristId(id));
        return actorWithMovie;
    }

    @ApiOperation("更新演员信息")
    @PutMapping("/actor")
    public boolean updateActor(Actor actor) {
        return actorService.updateById(actor);
    }

    @ApiOperation("添加演员")
    @PostMapping("/actor")
    public boolean insertActor(Actor actor) {
        return actorService.save(actor);
    }

    @ApiOperation("按id删除演员")
    @DeleteMapping("/actor/{id}")
    public boolean delActor(@PathVariable(value = "id") Integer id) {
        return actorService.removeById(id);
    }


    @ApiOperation("获取所有演员")
    @GetMapping("/actors")
    public MgtActorListVo getActors() {
        List<ActorWithMovie> actorWithMovies = new ArrayList<>();

        List<Actor> actors = actorService.list();

        for (Actor actor: actors) {
            ActorWithMovie actorWithMovie = new ActorWithMovie();
            actorWithMovie.setActor(actor);
            actorWithMovies.add(actorWithMovie);
        }

        return new MgtActorListVo(actorWithMovies);

    }

    @ApiOperation("csv文件导入演员数据")
    @PostMapping("/actor/csvInsert")
    public boolean insertActorByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        HeaderColumnNameMappingStrategy<Actor> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Actor.class);
        CsvToBean<Actor> build = new CsvToBeanBuilder<Actor>(in).withMappingStrategy(mapper).build();
        List<Actor> actorList = build.parse();
        return actorService.saveBatch(actorList);
    }

    @ApiOperation("获取票房top10演员")
    @GetMapping("/actor/boxTop")
    public List<PeopleWithBox> getTopActorWithBox() {
        return actorService.getTopActorWithBox();
    }

    //@ApiOperation("获得演员表总数")
    //@GetMapping("/actor/count")
    //public long getActorCount() {
    //    return actorService.getActorCount();
    //}


}
