package com.xzx.mapper;

import com.xzx.entity.Scenarist;

import java.util.List;

public interface ScenaristExtendMapper {

    public List<Scenarist> getScenaristByMovieId(Integer movieId);
}
