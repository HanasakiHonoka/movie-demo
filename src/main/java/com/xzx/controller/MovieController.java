package com.xzx.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.dto.MovieWithPeople;
import com.xzx.entity.Movie;
import com.xzx.servie.IActorService;
import com.xzx.servie.IDirectorService;
import com.xzx.servie.IMovieService;
import com.xzx.servie.IScenaristService;
import com.xzx.vo.MgtMovieListVo;
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
@Api(value = "MovieController", tags = "电影模块")
@RestController
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IActorService actorService;

    @Autowired
    private IDirectorService directorService;

    @Autowired
    private IScenaristService scenaristService;


    @ApiOperation("按id获取电影信息")
    @GetMapping("/movie/{id}")
    //public ResultVo<Movie> getMovie(@PathVariable String id) {
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "电影ID", example = "10"))

    public MovieWithPeople getMovie(@PathVariable(value = "id") String id) {
        MovieWithPeople movieWithPeople = new MovieWithPeople();
        movieWithPeople.setMovie(movieService.getById(Integer.parseInt(id)));
        movieWithPeople.setActors(actorService.getSimpleActorByMovieId(Integer.parseInt(id)));
        movieWithPeople.setDirectors(directorService.getSimpleDirectorByMovieId(Integer.parseInt(id)));
        movieWithPeople.setScenarists(scenaristService.getSimpleScenaristByMovieId(Integer.parseInt(id)));
        return movieWithPeople;
    }

    @ApiOperation("更新电影信息")
    @PutMapping("/movie")
    public boolean updateMovie(Movie movie) {
        return movieService.updateById(movie);
    }

    @ApiOperation("添加电影")
    @PostMapping("/movie")
    public boolean insertMovie(Movie movie) {
        return movieService.save(movie);
    }

    //@ApiOperation("批量添加电影")
    //@PostMapping("/movies")
    //public Integer insertMultiMovie(List<Movie> movies) {
    //    return null;
    //}

    @ApiOperation("按id删除电影")
    @DeleteMapping("/movie/{id}")
    public boolean delMovie(@PathVariable(value = "id") String id) {
        return movieService.removeById(Integer.parseInt(id));
    }

    @ApiOperation("获取所有电影")
    @GetMapping("/movies")
    public MgtMovieListVo getMovies() {
        List<MovieWithPeople> movieWithPeoples = new ArrayList<>();
        List<Movie> movies = movieService.list();
        for (Movie movie:movies) {
            MovieWithPeople movieWithPeople = new MovieWithPeople();
            movieWithPeople.setMovie(movie);
            movieWithPeoples.add(movieWithPeople);
        }
        return new MgtMovieListVo(movieWithPeoples);
    }

    @ApiOperation("csv文件导入电影数据")
    @PostMapping("/movie/csvInsert")
    public boolean insertMovieByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        HeaderColumnNameMappingStrategy<Movie> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Movie.class);
        CsvToBean<Movie> build = new CsvToBeanBuilder<Movie>(in).withMappingStrategy(mapper).build();
        List<Movie> movieList = build.parse();
        return movieService.saveBatch(movieList);
    }

    //@ApiOperation("获得电影表总数")
    //@GetMapping("/movie/count")
    //public long getMovieCount() {
    //    return movieService.getMovieCount();
    //}


}
