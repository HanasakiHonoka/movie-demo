package com.xzx.dto;

import com.xzx.entity.Director;
import com.xzx.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class DirectorWithMovie {

    private Director director;
    private List<SimpleMovie> AMovies;
    private List<SimpleMovie> DMovies;
    private List<SimpleMovie> SMovies;
}
