package com.xzx.util;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Actor;
import com.xzx.servie.MovieService;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public class SimpleMovieUtil {

    public static List<SimpleMovie> Limit5SimpleMovie(MovieService movieService, Integer id, String identity) {
        List<SimpleMovie> movies = new ArrayList<>();
        if(identity.equals("Actor")) {
            movies = movieService.getSimpleMovieByActorId(id);
        } else if (identity.equals("Director")) {
            movies = movieService.getSimpleMovieByDirectorId(id);
        } else if (identity.equals("Scenarist")){
            movies = movieService.getSimpleMovieByScenaristId(id);
        }

        if(movies.size() > 5){
            movies = movies.subList(0, 5);
        }
        return movies;
    }
}
