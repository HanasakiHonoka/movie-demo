package com.xzx.dto;

import com.xzx.entity.Scenarist;
import lombok.Data;

import java.util.List;

@Data
public class ScenaristWithMovie {

    private Scenarist scenarist;
    private List<SimpleMovie> AMovies;
    private List<SimpleMovie> DMovies;
    private List<SimpleMovie> SMovies;
}
