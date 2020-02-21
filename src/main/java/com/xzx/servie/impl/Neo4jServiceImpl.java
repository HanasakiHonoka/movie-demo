package com.xzx.servie.impl;

import com.xzx.dto.NeoPeopleRelationDto;
import com.xzx.entity.NeoMovie;
import com.xzx.entity.NeoPeople;
import com.xzx.entity.NeoRelation;
import com.xzx.servie.Neo4jService;
import com.xzx.util.Neo4jUtil;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Neo4jServiceImpl implements Neo4jService {


    @Autowired
    private Neo4jUtil neo4jUtil;

    @Override
    public List<NeoPeopleRelationDto> getPeopleRelation(String uid) {
        //获得driver和session
        Driver driver = neo4jUtil.getDriver();
        Session session = driver.session();
        //1274297
        String cql = String.format("match (p1:person)-[r1]->(m:movie) where p1.uid = '%s' return p1,r1,m limit 10", uid);
        //获得查询结果
        Result result = session.run(cql);
        List<NeoPeopleRelationDto> res = new ArrayList<>();
        while (result.hasNext()) {
            //遍历每行
            Record record = result.next();
            NeoPeopleRelationDto nprd = new NeoPeopleRelationDto();
            for (String index:record.keys()) {
                //遍历record的每个项
                if (index.equals("p1")) {
                    //第一个人，也就是中心点
                    Map<String, Object> peopleMap = record.get(index).asMap();
                    //NeoPeople neoPeople = new NeoPeople(peopleMap.get("uid").toString(), peopleMap.get("name").toString());
                    //System.out.println(neoPeople);
                    nprd.setNeoPeople(new NeoPeople(peopleMap.get("uid").toString(), peopleMap.get("name").toString()));
                } else if (index.equals("r1")) {
                    //关系
                    Map<String, Object> RelationMap = record.get(index).asMap();
                    nprd.setNeoRelation(RelationMap.get("relation").toString());
                } else if (index.equals("m")) {
                    //电影
                    Map<String, Object> movieMap = record.get(index).asMap();
                    nprd.setNeoMovie(new NeoMovie(movieMap.get("mid").toString(), movieMap.get("name").toString()));
                }
            }
            res.add(nprd);

        }
        session.close();
        driver.close();

        return res;
    }
}
