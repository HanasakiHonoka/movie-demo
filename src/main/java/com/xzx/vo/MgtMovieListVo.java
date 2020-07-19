package com.xzx.vo;

import com.xzx.dto.MovieWithPeople;
import com.xzx.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MgtMovieListVo {

    private List<MovieWithPeople> movies;

}
