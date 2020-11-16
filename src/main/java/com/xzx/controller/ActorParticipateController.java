package com.xzx.controller;

import com.xzx.entity.Actor;
import com.xzx.entity.ActorParticipate;
import com.xzx.servie.IActorParticipateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname ActorParticipateController
 * @Description
 * @Date 2020/11/6 23:15
 * @Author XZX
 * @Version 1.0
 */
@Slf4j
@Api(value = "ActorParticipateController", tags = "演员电影参演模块")
@RestController
@RequestMapping("/ap")
public class ActorParticipateController {

    @Autowired
    IActorParticipateService actorParticipateService;

    @ApiOperation("按电影id进行查找")
    @GetMapping("/getByMovieId")
    public List<ActorParticipate> getByMovieId(Integer movieId) {
        return actorParticipateService.getByMovieId(movieId);
    }
    @ApiOperation("按演员id查找")
    @GetMapping("/getByActorId")
    public List<ActorParticipate> getByActorId(Integer actorId) {
        return actorParticipateService.getByActorId(actorId);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public boolean update(ActorParticipate actorParticipate) {
        return actorParticipateService.update(actorParticipate);
    }
}
