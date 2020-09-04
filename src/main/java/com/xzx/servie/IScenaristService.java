package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.PeopleWithBox;
import com.xzx.dto.SimpleScenarist;
import com.xzx.entity.Scenarist;
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
public interface IScenaristService extends IService<Scenarist> {

    List<SimpleScenarist> getSimpleScenaristByMovieId(Integer movieId);

    List<Scenarist> getLikeScenaristWithLimit(SearchVo searchVo);

    long getLikeScenaristCount(SearchVo searchVo);

    List<Scenarist> getFirstLikeScenarist(HintVo hintVo);

    List<PeopleWithBox> getTopScenaristWithBox();
}
