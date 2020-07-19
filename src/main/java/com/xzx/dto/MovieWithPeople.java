package com.xzx.dto;

import com.xzx.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieWithPeople {
    private Movie movie;
    private List<SimpleActor> actors;
    private List<SimpleDirector> directors;
    private List<SimpleScenarist> scenarists;

}
