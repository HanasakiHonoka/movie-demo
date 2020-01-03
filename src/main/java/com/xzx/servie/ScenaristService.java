package com.xzx.servie;

import com.xzx.dto.SimpleScenarist;
import com.xzx.entity.Director;
import com.xzx.entity.Scenarist;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ScenaristService {

    public List<Scenarist> getLikeScenaristWithLimit(SearchVo searchVo);

    public List<Scenarist> getFirstLikeScenarist(HintVo hintVo);

    public long getLikeScenaristCount(SearchVo searchVo);

    public Scenarist getScenarist(Integer id);

    public List<SimpleScenarist> getSimpleScenaristByMovieId(Integer movieId);

    public List<Scenarist> getScenaristByName(String name);
}
