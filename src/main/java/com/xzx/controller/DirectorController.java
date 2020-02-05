package com.xzx.controller;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.dto.DirectorWithMovie;
import com.xzx.entity.Director;
import com.xzx.entity.Scenarist;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.vo.MgtDirectorListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation("更新导演信息")
    @PutMapping("/director")
    public Integer updateDirector(Director director) {
        return directorService.updateDirector(director);
    }

    @ApiOperation("添加导演")
    @PostMapping("/director")
    public Integer insertDirector(Director director) {
        return directorService.insertDirector(director);
    }

    @ApiOperation("按id删除导演")
    @DeleteMapping("/director/{id}")
    public Integer delDirector(@PathVariable(value = "id") Integer id) {
        return directorService.delDirector(id);
    }

    @ApiOperation("获取所有导演")
    @GetMapping("/directors")
    public MgtDirectorListVo getDirectors() {
        List<DirectorWithMovie> directorWithMovies = new ArrayList<>();

        List<Director> directors = directorService.getDirectors();
        for (Director director: directors) {
            DirectorWithMovie directorWithMovie = new DirectorWithMovie();
            directorWithMovie.setDirector(director);
            directorWithMovies.add(directorWithMovie);
        }

        return new MgtDirectorListVo(directorWithMovies);
    }

    @ApiOperation("csv文件导入导演数据")
    @PostMapping("/director/csvInsert")
    public Integer insertScenaristByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), "utf-8");
        HeaderColumnNameMappingStrategy<Director> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Director.class);
        Integer res = 0;
        CsvToBean<Director> build = new CsvToBeanBuilder<Director>(in).withMappingStrategy(mapper).build();
        List<Director> directorList = build.parse();
        try {
            res = directorService.insertMulti(directorList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res = 0;
        } finally {
            return res;
        }
    }

    //@ApiOperation("获得导演表总数")
    //@GetMapping("/director/count")
    //public long getDirectorCount() {
    //    return directorService.getDirectorCount();
    //}

}
