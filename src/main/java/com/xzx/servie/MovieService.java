package com.xzx.servie;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Movie;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {

    public List<Movie> getLikeMovieWithLimit(SearchVo searchVo);

    public List<Movie> getFirstLikeMovie(HintVo hintVo);

    public long getLikeMovieCount(SearchVo searchVo);

    public long getMovieCount();

    public Movie getMovie(Integer id);

    public List<Movie> getMovies();

    public Integer insertMulti(List<Movie> movies);

    public Integer updateMovie(Movie movie);

    public Integer insertMovie(Movie movie);

    public Integer delMovie(Integer movieId);

    public List<SimpleMovie> getSimpleMovieByActorId(Integer actorId);

    public List<SimpleMovie> getSimpleMovieByDirectorId(Integer directorId);

    public List<SimpleMovie> getSimpleMovieByScenaristId(Integer scenaristId);
}
