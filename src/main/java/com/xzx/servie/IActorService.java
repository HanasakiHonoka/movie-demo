package com.xzx.servie;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.ActorQueryDTO;
import com.xzx.dto.PeopleWithBox;
import com.xzx.dto.SimpleActor;
import com.xzx.entity.Actor;
import com.xzx.vo.HintVo;
import com.xzx.vo.MgtActorPageVO;
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
public interface IActorService extends IService<Actor> {

    IPage<MgtActorPageVO> getActorPage(ActorQueryDTO actorQueryDTO);

    List<SimpleActor> getSimpleActorByMovieId(int movieId);

    List<SimpleActor> getNotShownSimpleActorByMovieId(int movieId);

    List<Actor> getLikeActorWithLimit(SearchVo searchVo);

    long getLikeActorCount(SearchVo searchVo);

    List<Actor> getFirstLikeActor(HintVo hintVo);

    List<PeopleWithBox> getTopActorWithBox();

    List<PeopleWithBox> getTopFemaleActorWithBox();

    List<PeopleWithBox> getTopMaleActorWithBox();

}
