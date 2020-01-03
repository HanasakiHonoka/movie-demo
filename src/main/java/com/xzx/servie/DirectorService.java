package com.xzx.servie;

import com.xzx.dto.SimpleDirector;
import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DirectorService {

    public List<Director> getLikeDirectorWithLimit(SearchVo searchVo);

    public List<Director> getFirstLikeDirector(HintVo hintVo);

    public long getLikeDirectorCount(SearchVo searchVo);

    public Director getDirector(Integer id);

    public List<SimpleDirector> getSimpleDirectorByMovieId(Integer movieId);

    public List<Director> getDirectorByName(String name);
}
