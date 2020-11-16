package com.xzx.servie.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.dto.DirectorQueryDTO;
import com.xzx.dto.PeopleWithBox;
import com.xzx.dto.SimpleDirector;
import com.xzx.entity.Director;
import com.xzx.mapper.DirectorMapper;
import com.xzx.servie.IDirectorService;
import com.xzx.vo.HintVo;
import com.xzx.vo.MgtDirectorPageVO;
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
public class DirectorServiceImpl extends ServiceImpl<DirectorMapper, Director> implements IDirectorService {

    @Autowired
    DirectorMapper directorMapper;

    @Override
    public IPage<MgtDirectorPageVO> getDirectorPage(DirectorQueryDTO directorQueryDTO) {
        Page<Director> page = new Page<>(directorQueryDTO.getPage(), directorQueryDTO.getPageSize());
        QueryWrapper<Director> queryWrapper = new QueryWrapper<>();
        if (directorQueryDTO.getGender() != null) {
            queryWrapper.eq(Director.GENDER, directorQueryDTO.getGender() ? "男":"女");
        }
        if (directorQueryDTO.getBirthday() != null) {
            queryWrapper.orderBy(true, directorQueryDTO.getBirthday(), Director.BIRTHDAY);
        }
        Page<Director> data = directorMapper.selectPage(page, queryWrapper);
        List<Director> directorList = data.getRecords();
        List<MgtDirectorPageVO> voList = new ArrayList<>();
        for (Director director : directorList) {
            MgtDirectorPageVO mgtDirectorPageVO = BeanUtil.copyProperties(director, MgtDirectorPageVO.class);
            voList.add(mgtDirectorPageVO);
        }
        IPage<MgtDirectorPageVO> res = new Page<>();
        res.setTotal(data.getTotal());
        res.setCurrent(data.getCurrent());
        res.setSize(data.getSize());
        res.setRecords(voList);
        return res;
    }

    @Override
    public List<SimpleDirector> getSimpleDirectorByMovieId(int movieId) {
        List<Director> directors = directorMapper.getDirectorByMovieId(movieId);
        List<SimpleDirector> simpleDirectors = new ArrayList<>();
        for(Director director:directors) {
            simpleDirectors.add(new SimpleDirector(director.getId(), director.getName()));
        }
        return simpleDirectors;
    }

    @Override
    public List<Director> getLikeDirectorWithLimit(SearchVo searchVo) {
        int pageSize = searchVo.getPageSize();
        QueryWrapper<Director> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        wrapper.last("limit " + (Integer.parseInt(searchVo.getPage()) - 1) * pageSize + "," + pageSize);
        return this.list(wrapper);
    }

    @Override
    public long getLikeDirectorCount(SearchVo searchVo) {
        QueryWrapper<Director> wrapper = new QueryWrapper<>();
        wrapper.like("name", searchVo.getWords());
        return this.count(wrapper);
    }

    @Override
    public List<Director> getFirstLikeDirector(HintVo hintVo) {
        QueryWrapper<Director> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", hintVo.getWords());
        wrapper.last("limit 0,10");
        return this.list(wrapper);
    }

    @Override
    public List<PeopleWithBox> getTopDirectorWithBox() {
        return directorMapper.getTopDirectorWithBox();
    }
}
