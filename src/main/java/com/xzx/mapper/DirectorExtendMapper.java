package com.xzx.mapper;

import com.xzx.entity.Director;

import java.util.List;

public interface DirectorExtendMapper {

    public List<Director> getDirectorByMovieId(Integer movieId);

    public Integer insertMulti(List<Director> directors);
}
