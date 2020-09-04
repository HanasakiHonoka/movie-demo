package com.xzx.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.dto.PeopleWithBox;
import com.xzx.dto.ScenaristWithMovie;
import com.xzx.entity.Scenarist;
import com.xzx.servie.IMovieService;
import com.xzx.servie.IScenaristService;
import com.xzx.vo.MgtScenaristListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Api(value = "ScenaristController", tags = "编剧模块")
@RestController
public class ScenaristController {

    @Autowired
    private IScenaristService scenaristService;

    @Autowired
    private IMovieService movieService;

    @ApiOperation("按id获取编剧信息")
    @GetMapping("/scenarist/{id}")
    public ScenaristWithMovie getScenarist(@PathVariable(value = "id") Integer id) {

        Scenarist scenarist = scenaristService.getById(id);
        ScenaristWithMovie scenaristWithMovie = new ScenaristWithMovie();
        scenaristWithMovie.setScenarist(scenarist);
        scenaristWithMovie.setAMovies(movieService.getSimpleMovieByActorId(id));
        scenaristWithMovie.setDMovies(movieService.getSimpleMovieByDirectorId(id));
        scenaristWithMovie.setSMovies(movieService.getSimpleMovieByScenaristId(id));

        return scenaristWithMovie;
    }

    @ApiOperation("更新编剧信息")
    @PutMapping("/scenarist")
    public boolean updateScenarist(Scenarist scenarist) {
        return scenaristService.updateById(scenarist);
    }

    @ApiOperation("添加编剧")
    @PostMapping("/scenarist")
    public boolean insertScenarist(Scenarist scenarist) {
        return scenaristService.save(scenarist);
    }

    @ApiOperation("按id删除编剧")
    @DeleteMapping("/scenarist/{id}")
    public boolean delScenarist(@PathVariable(value = "id") Integer id) {
        return scenaristService.removeById(id);
    }

    @ApiOperation("获取所有编剧")
    @GetMapping("/scenarists")
    public MgtScenaristListVo getScenarists() {
        List<ScenaristWithMovie> scenaristWithMovies = new ArrayList<>();

        List<Scenarist> scenarists = scenaristService.list();
        for (Scenarist scenarist:scenarists) {
            ScenaristWithMovie scenaristWithMovie = new ScenaristWithMovie();
            scenaristWithMovie.setScenarist(scenarist);
            scenaristWithMovies.add(scenaristWithMovie);
        }
        return new MgtScenaristListVo(scenaristWithMovies);
    }

    @ApiOperation("csv文件导入编剧数据")
    @PostMapping("/scenarist/csvInsert")
    public boolean insertScenaristByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        HeaderColumnNameMappingStrategy<Scenarist> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Scenarist.class);
        CsvToBean<Scenarist> build = new CsvToBeanBuilder<Scenarist>(in).withMappingStrategy(mapper).build();
        List<Scenarist> scenaristList = build.parse();
        return scenaristService.saveBatch(scenaristList);
    }

    @ApiOperation("获取票房top10编剧")
    @GetMapping("/scenarist/boxTop")
    public List<PeopleWithBox> getTopScenaristWithBox() {
        return scenaristService.getTopScenaristWithBox();
    }

    //@ApiOperation("获得编剧表总数")
    //@GetMapping("/scenarist/count")
    //public long getScenaristCount() {
    //    return scenaristService.getScenaristCount();
    //}
}
