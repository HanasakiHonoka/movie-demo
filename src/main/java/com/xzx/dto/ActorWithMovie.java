package com.xzx.dto;

import com.xzx.dto.SimpleMovie;
import com.xzx.entity.Actor;
import lombok.Data;

import java.util.List;

@Data
public class ActorWithMovie {

    private Actor actor;
    private List<SimpleMovie> AMovies;
    private List<SimpleMovie> DMovies;
    private List<SimpleMovie> SMovies;
}
