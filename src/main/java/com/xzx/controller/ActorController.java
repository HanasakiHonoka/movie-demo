package com.xzx.controller;

import com.xzx.entity.Actor;
import com.xzx.servie.ActorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ActorController", tags = "演员模块")
@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @ApiOperation("按id获取导演信息")
    @GetMapping("/actor/{id}")
    public Actor getActor(@PathVariable(value = "id") Integer id) {

        return actorService.getActor(id);
    }


}