package com.xzx.servie.impl;

import com.xzx.dto.NeoPeopleRelationDto;
import com.xzx.entity.NeoMovie;
import com.xzx.entity.NeoPeople;
import com.xzx.servie.Neo4jService;
import com.xzx.util.ListUtil;
import com.xzx.util.Neo4jUtil;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Neo4jServiceImpl implements Neo4jService {


    @Autowired
    private Neo4jUtil neo4jUtil;


    @Override
    public List<NeoPeopleRelationDto> getPeopleRelation(String uid) {
        //获得driver和session
        Session session = neo4jUtil.getSession();
        //1274297
        String cql = String.format("match (p1:person)-[r1]->(m:movie)" +
                "where p1.uid = '%s' " +
                "with p1,m,r1 " +
                "order by m " +
                "match l=((p2:person)-[r2]->(m)) " +
                "with l,p1,m,r1 " +
                "return p1,r1,m,collect(l) as rela", uid);
        //获得查询结果
        Result result = session.run(cql);
        List<NeoPeopleRelationDto> res = new ArrayList<>();
        Map<String, Integer> countMapR1 = new HashMap<>();
        countMapR1.put("导演", 0);
        countMapR1.put("参演", 0);
        countMapR1.put("编写", 0);
        while (result.hasNext()) {
            //遍历每行
            Record record = result.next();
            NeoPeopleRelationDto nprd1 = new NeoPeopleRelationDto();
            //先判断关系，确定是否达到上限
            String relationType = record.get("r1").asMap().get("relation").toString();
            int relationCount = countMapR1.get(relationType);
            if(relationType.equals("参演") && relationCount > 5) continue;
            if(relationType.equals("导演") && relationCount > 0) continue;
            if(relationType.equals("编写") && relationCount > 0) continue;
            countMapR1.put(relationType, relationCount + 1);
            nprd1.setNeoRelation(relationType);
            //System.out.println("record = " + record);
            for (String index:record.keys()) {

                //遍历record的每个项
                if (index.equals("p1")) {
                    //第一个人，也就是中心点
                    Map<String, Object> peopleMap = record.get(index).asMap();
                    //NeoPeople neoPeople = new NeoPeople(peopleMap.get("uid").toString(), peopleMap.get("name").toString());
                    //System.out.println(neoPeople);
                    nprd1.setStart(new NeoPeople("p" + peopleMap.get("uid").toString(), peopleMap.get("name").toString()));
                } else if (index.equals("r1")) {
                    //关系1
                    //Map<String, Object> RelationMap = record.get(index).asMap();
                    //String relationType = RelationMap.get("relation").toString();
                    //nprd1.setNeoRelation(relationType);
                    //countMapR1.put(RelationMap.get("relation").toString(), )
                } else if (index.equals("m")) {
                    //电影
                    Map<String, Object> movieMap = record.get(index).asMap();
                    nprd1.setEnd(new NeoMovie("m" + movieMap.get("mid").toString(), movieMap.get("name").toString()));
                } else if (index.equals("rela")) {
                    //System.out.println("record.get(index) = " + record.get(index));
                    //设置限制返回的数量
                    Map<String, Integer> countMapR2 = new HashMap<>();
                    countMapR2.put("导演", 0);
                    countMapR2.put("参演", 0);
                    countMapR2.put("编写", 0);
                    //获得外层关系集合
                    List<Object> pathList = record.get(index).asList();
                    //System.out.println("pathList = " + pathList);
                    for (Object path1:pathList) {
                        NeoPeopleRelationDto nprd2 = new NeoPeopleRelationDto();
                        Path path = (Path) path1;
                        //System.out.println(path.start().asMap().get("name"));
                        String relationType2 = "";
                        Iterator<Relationship> iterator = path.relationships().iterator();
                        while (iterator.hasNext()) {
                            Relationship rela = iterator.next();
                            relationType2 = rela.asMap().get("relation").toString();
                        }
                        //System.out.println(relationType);
                        Map<String, Object> peopleMap = path.start().asMap();
                        Map<String, Object> movieMap = path.end().asMap();
                        int relationCount2 = countMapR2.get(relationType2);
                        if(relationType2.equals("导演") && relationCount2 > 0) continue;
                        if(relationType2.equals("参演") && relationCount2 > 0) continue;
                        if(relationType2.equals("编写") && relationCount2 > 0) continue;
                        nprd2.setStart(new NeoPeople("p" + peopleMap.get("uid").toString(), peopleMap.get("name").toString()));
                        nprd2.setEnd(new NeoMovie("m" + movieMap.get("mid").toString(), movieMap.get("name").toString()));
                        nprd2.setNeoRelation(relationType2);
                        res.add(nprd2);
                        countMapR2.put(relationType2, relationCount2 + 1);
                    }

                }
            }
            res.add(nprd1);

        }
        session.close();

        return ListUtil.removeDuplicate(res);
        //return res;
    }

    @Override
    public List<NeoPeopleRelationDto> getMovieRelation(String mid) {
        Session session = neo4jUtil.getSession();
        String cql = String.format(
                "match l = ((p)-[r]->(m)) " +
                "where m.mid = '%s' " +
                "return collect(l) as rela", mid);
        Result result = session.run(cql);
        List<NeoPeopleRelationDto> res = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            for (String index:record.keys()) {
                if (index.equals("rela")) {
                    //设置限制返回的数量
                    Map<String, Integer> countMapR2 = new HashMap<>();
                    countMapR2.put("导演", 0);
                    countMapR2.put("参演", 0);
                    countMapR2.put("编写", 0);
                    List<Object> pathList = record.get(index).asList();
                    for (Object path1:pathList) {
                        NeoPeopleRelationDto nprd = new NeoPeopleRelationDto();
                        Path path = (Path) path1;
                        //System.out.println(path.start().asMap().get("name"));
                        String relationType2 = "";
                        Iterator<Relationship> iterator = path.relationships().iterator();
                        while (iterator.hasNext()) {
                            Relationship rela = iterator.next();
                            relationType2 = rela.asMap().get("relation").toString();
                        }
                        //System.out.println(relationType);
                        Map<String, Object> peopleMap = path.start().asMap();
                        Map<String, Object> movieMap = path.end().asMap();
                        int relationCount2 = countMapR2.get(relationType2);
                        if(relationType2.equals("导演") && relationCount2 > 0) continue;
                        if(relationType2.equals("参演") && relationCount2 > 5) continue;
                        if(relationType2.equals("编写") && relationCount2 > 0) continue;
                        nprd.setStart(new NeoPeople("p" + peopleMap.get("uid").toString(), peopleMap.get("name").toString()));
                        nprd.setEnd(new NeoMovie("m" + movieMap.get("mid").toString(), movieMap.get("name").toString()));
                        nprd.setNeoRelation(relationType2);
                        res.add(nprd);
                        countMapR2.put(relationType2, relationCount2 + 1);
                    }
                }
            }
        }


        session.close();
        return res;
    }


}
