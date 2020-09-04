package com.xzx.servie;

import com.xzx.dto.NeoPeopleRelationDto;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.Neo4jMovieVo;
import com.xzx.vo.Neo4jPersonVo;

import java.util.List;

public interface Neo4jService {

    List<NeoPeopleRelationDto> getPeopleRelation(Neo4jPersonVo neo4jPersonVo);

    List<NeoPeopleRelationDto> getMovieRelation(Neo4jMovieVo neo4jMovieVo);

    Double getMoviePrScore(BoxCalculateVo boxCalculateVo);
}
