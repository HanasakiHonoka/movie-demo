package com.xzx.servie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.PeopleWithBox;
import com.xzx.dto.SimpleScenarist;
import com.xzx.entity.Scenarist;
import com.xzx.mapper.ScenaristMapper;
import com.xzx.servie.IScenaristService;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Service
public class ScenaristServiceImpl extends ServiceImpl<ScenaristMapper, Scenarist> implements IScenaristService {

    @Autowired
    ScenaristMapper scenaristMapper;

    @Override
    public List<SimpleScenarist> getSimpleScenaristByMovieId(Integer movieId) {
        List<Scenarist> scenarists = scenaristMapper.getScenaristByMovieId(movieId);
        List<SimpleScenarist> simpleScenarists = new ArrayList<>();
        for(Scenarist scenarist:scenarists) {
            simpleScenarists.add(new SimpleScenarist(scenarist.getId(), scenarist.getName()));
        }
        return simpleScenarists;
    }

    @Override
    public List<Scenarist> getLikeScenaristWithLimit(SearchVo searchVo) {
        int pageSize = searchVo.getPageSize();
        QueryWrapper<Scenarist> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        wrapper.last("limit " + (Integer.parseInt(searchVo.getPage()) - 1) * pageSize + "," + pageSize);
        return this.list(wrapper);
    }

    @Override
    public long getLikeScenaristCount(SearchVo searchVo) {
        QueryWrapper<Scenarist> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        return this.count(wrapper);
    }

    @Override
    public List<Scenarist> getFirstLikeScenarist(HintVo hintVo) {
        QueryWrapper<Scenarist> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", hintVo.getWords());
        wrapper.last("limit 0,10");
        return this.list(wrapper);
    }

    @Override
    public List<PeopleWithBox> getTopScenaristWithBox() {
        return scenaristMapper.getTopScenaristWithBox();
    }
}
