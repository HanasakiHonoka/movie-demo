package com.xzx.servie;

import com.xzx.entity.Movie;
import com.xzx.vo.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {

    public List<Movie> getLikeMovieWithLimit(SearchVo searchVo);

    public List<Movie> getLikeMovie(SearchVo searchVo);

    public Movie getMovie(Integer id);
}
