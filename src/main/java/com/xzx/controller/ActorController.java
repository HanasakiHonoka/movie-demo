package com.xzx.controller;

import com.xzx.entity.Actor;
import com.xzx.servie.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actor/{id}")
    public Actor getActor(@PathVariable(value = "id") Integer id) {

        return actorService.getActor(id);
    }


}
