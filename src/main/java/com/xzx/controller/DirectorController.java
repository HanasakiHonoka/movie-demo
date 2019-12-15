package com.xzx.controller;


import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.servie.ActorService;
import com.xzx.servie.DirectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "DirectorController", tags = "导演模块")
@RestController
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @ApiOperation("按id获取导演信息")
    @GetMapping("/director/{id}")
    public Director getDirector(@PathVariable(value = "id") Integer id) {

        return directorService.getDirector(id);
    }

}
