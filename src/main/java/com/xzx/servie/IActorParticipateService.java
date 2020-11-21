package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.entity.ActorParticipate;
import com.xzx.vo.ActorParticipateVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Classname IActorParticipate
 * @Description
 * @Date 2020/11/6 22:18
 * @Author XZX
 * @Version 1.0
 */
public interface IActorParticipateService extends IService<ActorParticipate> {
    List<ActorParticipate> getByMovieId(Integer movieId);

    List<ActorParticipateVO> getVOByMovieId(Integer movieId);

    List<ActorParticipate> getByActorId(Integer ActorId);

    List<ActorParticipateVO> getVOByActorId(Integer ActorId);

    boolean update(ActorParticipate actorParticipate);
}
