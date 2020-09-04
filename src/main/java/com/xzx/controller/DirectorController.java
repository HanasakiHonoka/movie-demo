package com.xzx.controller;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.dto.DirectorWithMovie;
import com.xzx.dto.PeopleWithBox;
import com.xzx.entity.Director;
import com.xzx.servie.IDirectorService;
import com.xzx.servie.IMovieService;
import com.xzx.vo.MgtDirectorListVo;
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

@Api(value = "DirectorController", tags = "导演模块")
@RestController
public class DirectorController {

    @Autowired
    private IDirectorService directorService;

    @Autowired
    private IMovieService movieService;

    @ApiOperation("按id获取导演信息")
    @GetMapping("/director/{id}")
    public DirectorWithMovie getDirector(@PathVariable(value = "id") Integer id) {

        Director director = directorService.getById(id);
        DirectorWithMovie directorWithMovie = new DirectorWithMovie();
        directorWithMovie.setDirector(director);
        directorWithMovie.setAMovies(movieService.getSimpleMovieByActorId(id));
        directorWithMovie.setDMovies(movieService.getSimpleMovieByDirectorId(id));
        directorWithMovie.setSMovies(movieService.getSimpleMovieByScenaristId(id));
        return directorWithMovie;
    }

    @ApiOperation("更新导演信息")
    @PutMapping("/director")
    public boolean updateDirector(Director director) {
        return directorService.updateById(director);
    }

    @ApiOperation("添加导演")
    @PostMapping("/director")
    public boolean insertDirector(Director director) {
        return directorService.save(director);
    }

    @ApiOperation("按id删除导演")
    @DeleteMapping("/director/{id}")
    public boolean delDirector(@PathVariable(value = "id") Integer id) {
        return directorService.removeById(id);
    }

    @ApiOperation("获取所有导演")
    @GetMapping("/directors")
    public MgtDirectorListVo getDirectors() {
        List<DirectorWithMovie> directorWithMovies = new ArrayList<>();

        List<Director> directors = directorService.list();
        for (Director director: directors) {
            DirectorWithMovie directorWithMovie = new DirectorWithMovie();
            directorWithMovie.setDirector(director);
            directorWithMovies.add(directorWithMovie);
        }

        return new MgtDirectorListVo(directorWithMovies);
    }

    @ApiOperation("csv文件导入导演数据")
    @PostMapping("/director/csvInsert")
    public boolean insertScenaristByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        HeaderColumnNameMappingStrategy<Director> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Director.class);
        CsvToBean<Director> build = new CsvToBeanBuilder<Director>(in).withMappingStrategy(mapper).build();
        List<Director> directorList = build.parse();
        return directorService.saveBatch(directorList);
    }

    @ApiOperation("获取票房top10导演")
    @GetMapping("/director/boxTop")
    public List<PeopleWithBox> getTopDirectorWithBox() {
        return directorService.getTopDirectorWithBox();
    }

    //@ApiOperation("获得导演表总数")
    //@GetMapping("/director/count")
    //public long getDirectorCount() {
    //    return directorService.getDirectorCount();
    //}

}
