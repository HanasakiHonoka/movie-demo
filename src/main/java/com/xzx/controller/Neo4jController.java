package com.xzx.controller;

import com.xzx.dto.NeoPeopleRelationDto;
import com.xzx.servie.Neo4jService;
import com.xzx.util.Neo4jUtil;
import com.xzx.vo.Neo4jMovieVo;
import com.xzx.vo.Neo4jPersonVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Neo4jController", tags = "Neo4j模块")
@RestController
public class Neo4jController {

    @Autowired
    Neo4jService neo4jService;



    @ApiOperation("获得以人为中心的关系")
    @GetMapping("/neo/person")
    public List<NeoPeopleRelationDto> getPersonRelation(Neo4jPersonVo neo4jPersonVo) {
        return neo4jService.getPeopleRelation(neo4jPersonVo);

    }

    @ApiOperation("获得以电影为中心的关系")
    @GetMapping("/neo/movie/{id}")
    public List<NeoPeopleRelationDto> getMovieRelation(Neo4jMovieVo neo4jMovieVo) {
        return neo4jService.getMovieRelation(neo4jMovieVo);

    }

}
