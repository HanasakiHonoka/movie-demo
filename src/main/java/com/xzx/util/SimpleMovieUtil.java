package com.xzx.util;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Actor;
import com.xzx.entity.Movie;
import com.xzx.servie.IMovieService;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public class SimpleMovieUtil {

    public static List<SimpleMovie> getSimpleMovieByPersonId(IMovieService movieService, Integer id, String identity, Integer movieNumLimit) {
        List<SimpleMovie> movies = new ArrayList<>();
        if(identity.equals("Actor")) {
            movies = movieService.getSimpleMovieByActorId(id);
        } else if (identity.equals("Director")) {
            movies = movieService.getSimpleMovieByDirectorId(id);
        } else if (identity.equals("Scenarist")){
            movies = movieService.getSimpleMovieByScenaristId(id);
        }

        if(movies.size() > movieNumLimit){
            movies = movies.subList(0, movieNumLimit);
        }
        return movies;
    }

    public static List<SimpleMovie> movieToSimpleMovie(List<Movie> movies) {
        List<SimpleMovie> simpleMovies = new ArrayList<>();
        for (Movie movie: movies) {
            simpleMovies.add(new SimpleMovie(movie.getId(), movie.getTitle(), movie.getBoxoffice(), movie.getReleaseTime()));
        }
        return simpleMovies;
    }
}
