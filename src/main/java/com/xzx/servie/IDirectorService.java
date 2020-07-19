package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.SimpleDirector;
import com.xzx.entity.Director;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
public interface IDirectorService extends IService<Director> {

    List<SimpleDirector> getSimpleDirectorByMovieId(int movieId);

    List<Director> getLikeDirectorWithLimit(SearchVo searchVo);

    long getLikeDirectorCount(SearchVo searchVo);

    List<Director> getFirstLikeDirector(HintVo hintVo);
}
