package com.xzx.servie;

import com.xzx.entity.Director;
import com.xzx.entity.Scenarist;
import com.xzx.vo.SearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ScenaristService {

    public List<Scenarist> getLikeScenaristWithLimit(SearchVo searchVo);

    public long getLikeScenaristCount(SearchVo searchVo);

    public Scenarist getScenarist(Integer id);
}
