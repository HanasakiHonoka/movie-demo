package com.xzx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.xzx.constant.MovieTypeEnum;
import com.xzx.dto.MovieQueryDTO;
import com.xzx.dto.MovieWithPeople;
import com.xzx.dto.SimpleMovie;
import com.xzx.dto.YearBoxOffice;
import com.xzx.entity.Movie;
import com.xzx.servie.IActorService;
import com.xzx.servie.IDirectorService;
import com.xzx.servie.IMovieService;
import com.xzx.servie.IScenaristService;
import com.xzx.vo.MgtMoviePageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @CacheEvict("movies")
    public boolean updateMovie(Movie movie) {
        return movieService.updateById(movie);
    }

    @ApiOperation("添加电影")
    @PostMapping("/movie")
    @CacheEvict("movies")
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
    @CacheEvict("movies")
    public boolean delMovie(@PathVariable(value = "id") String id) {
        return movieService.removeById(Integer.parseInt(id));
    }

    @ApiOperation("分页获取所有电影")
    @GetMapping("/moviePage")
    public IPage<MgtMoviePageVO> getMoviePage(MovieQueryDTO movieQueryDTO) {
        IPage<MgtMoviePageVO> moviePage = movieService.getMoviePage(movieQueryDTO);
        return moviePage;
    }

    @ApiOperation("获得每年票房top10电影")
    @GetMapping("/movie/boxTopYear")
    public List<SimpleMovie> getBoxTopYearMovie(@RequestParam(value = "year") String year) {
        return movieService.getYearBoxTopMovie(year);
    }

    @ApiOperation("csv文件导入电影数据")
    @PostMapping("/movie/csvInsert")
    @CacheEvict("movies")
    public boolean insertMovieByCsv(MultipartFile file) throws IOException {
        InputStreamReader in = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        HeaderColumnNameMappingStrategy<Movie> mapper = new HeaderColumnNameMappingStrategy<>();
        mapper.setType(Movie.class);
        CsvToBean<Movie> build = new CsvToBeanBuilder<Movie>(in).withMappingStrategy(mapper).build();
        List<Movie> movieList = build.parse();
        return movieService.saveBatch(movieList);
    }

    @ApiOperation("获得票房top10电影")
    @GetMapping("/movie/boxTop")
    public List<SimpleMovie> getBoxTopMovie() {
        return movieService.getTopMovie("boxoffice");
    }

    @ApiOperation("获取所有类型的电影数量")
    @GetMapping("/movie/typeCount")
    public Map<String, Integer> getMovieCountByType() {
        Map<String, Integer> res = new HashMap<>();
        for (MovieTypeEnum value : MovieTypeEnum.values()) {
            res.put(value.getRawName(), movieService.getMovieCountByType(value.getRawName()));
        }
        return res;
    }

    @ApiOperation("获得评分top10电影")
    @GetMapping("/movie/scoreTop")
    public List<SimpleMovie> getScoreTopMovie() {
        return movieService.getTopMovie("douban_rating");
    }

    @ApiOperation("获得各个年份电影票房总数")
    @GetMapping("/movie/boxYear")
    public List<YearBoxOffice> getMovieBoxOrderByYear() {
        List<YearBoxOffice> boxAllYear = movieService.getBoxAllYear();
        System.out.println(boxAllYear);
        return boxAllYear;
    }

    @ApiOperation("删除电影缓存")
    @DeleteMapping("/movie/delCache")
    @CacheEvict("movies")
    public void delCache() {

    }

    @ApiOperation("获取所有电影类型")
    @GetMapping("/movie/types")
    public List<String> getAllTypes() {
        MovieTypeEnum[] types = MovieTypeEnum.values();
        List<String> res = new ArrayList<>();
        for (MovieTypeEnum type : types) {
            res.add(type.getRawName());
        }
        return res;
    }

    //@ApiOperation("获得电影表总数")
    //@GetMapping("/movie/count")
    //public long getMovieCount() {
    //    return movieService.getMovieCount();
    //}


}
