package com.xzx.servie;

import com.xzx.dto.NeoPeopleRelationDto;

import java.util.List;

public interface Neo4jService {

    List<NeoPeopleRelationDto> getPeopleRelation(String uid);
}
