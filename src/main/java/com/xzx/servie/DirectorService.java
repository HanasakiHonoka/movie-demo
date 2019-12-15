package com.xzx.servie;

import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.vo.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DirectorService {

    public List<Director> getLikeDirectorWithLimit(SearchVo searchVo);

    public long getLikeDirectorCount(SearchVo searchVo);

    public Director getDirector(Integer id);
}
