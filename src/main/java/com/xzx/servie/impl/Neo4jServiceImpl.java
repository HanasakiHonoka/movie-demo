package com.xzx.servie.impl;

import com.alibaba.fastjson.JSONObject;
import com.xzx.constant.ConstantParam;
import com.xzx.dto.NeoPeopleRelationDto;
import com.xzx.entity.NeoMovie;
import com.xzx.entity.NeoPeople;
import com.xzx.servie.Neo4jService;
import com.xzx.util.Neo4jUtil;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.Neo4jMovieVo;
import com.xzx.vo.Neo4jPersonVo;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class Neo4jServiceImpl implements Neo4jService {

    @Override
    public List<NeoPeopleRelationDto> getPeopleRelation(Neo4jPersonVo neo4jPersonVo) {
        log.info(neo4jPersonVo.toString());
        //获得driver和session
        Session session = Neo4jUtil.getSession();
        int actorNum = neo4jPersonVo.getActorNum();
        int directorNum = neo4jPersonVo.getDirectorNum();
        int scenaristNum = neo4jPersonVo.getScenaristNum();

        int mActorNum = neo4jPersonVo.getMActorNum();
        int mDirectorNum = neo4jPersonVo.getMDirectorNum();
        int mScenaristNum = neo4jPersonVo.getMScenaristNum();

        //1274297
        String cql = String.format("match (p1:person)-[r1]->(m:movie)" +
                "where p1.uid = '%s' " +
                "with p1,m,r1 " +
                "order by m " +
                "match l=((p2:person)-[r2]->(m)) " +
                "with l,p1,m,r1 " +
                "return p1,r1,m,collect(l) as rela", neo4jPersonVo.getId());
        //获得查询结果
        Result result = session.run(cql);
        HashSet<NeoPeopleRelationDto> resSet = new HashSet<>();
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
            if(relationType.equals("参演") && relationCount >= actorNum) continue;
            if(relationType.equals("导演") && relationCount >= directorNum) continue;
            if(relationType.equals("编写") && relationCount >= scenaristNum) continue;
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
                        for (Relationship rela : path.relationships()) {
                            relationType2 = rela.asMap().get("relation").toString();
                        }
                        //System.out.println(relationType);
                        Map<String, Object> peopleMap = path.start().asMap();
                        Map<String, Object> movieMap = path.end().asMap();
                        int relationCount2 = countMapR2.get(relationType2);
                        if(relationType2.equals("参演") && relationCount2 >= mActorNum) continue;
                        if(relationType2.equals("导演") && relationCount2 >= mDirectorNum) continue;
                        if(relationType2.equals("编写") && relationCount2 >= mScenaristNum) continue;
                        nprd2.setStart(new NeoPeople("p" + peopleMap.get("uid").toString(), peopleMap.get("name").toString()));
                        nprd2.setEnd(new NeoMovie("m" + movieMap.get("mid").toString(), movieMap.get("name").toString()));
                        nprd2.setNeoRelation(relationType2);
                        resSet.add(nprd2);
                        countMapR2.put(relationType2, relationCount2 + 1);
                    }

                }
            }
            resSet.add(nprd1);

        }
        session.close();

        return new ArrayList<>(resSet);
    }

    @Override
    public List<NeoPeopleRelationDto> getMovieRelation(Neo4jMovieVo neo4jMovieVo) {
        log.info(neo4jMovieVo.toString());
        Session session = Neo4jUtil.getSession();
        int actorNum = neo4jMovieVo.getActorNum();
        int directorNum = neo4jMovieVo.getDirectorNum();
        int scenaristNum = neo4jMovieVo.getScenaristNum();
        
        String cql = String.format(
                "match l = ((p)-[r]->(m)) " +
                "where m.mid = '%s' " +
                "return collect(l) as rela", neo4jMovieVo.getId());
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
                        for (Relationship rela : path.relationships()) {
                            relationType2 = rela.asMap().get("relation").toString();
                        }
                        //System.out.println(relationType);
                        Map<String, Object> peopleMap = path.start().asMap();
                        Map<String, Object> movieMap = path.end().asMap();
                        int relationCount2 = countMapR2.get(relationType2);
                        if(relationType2.equals("导演") && relationCount2 >= directorNum) continue;
                        if(relationType2.equals("参演") && relationCount2 >= actorNum) continue;
                        if(relationType2.equals("编写") && relationCount2 >= scenaristNum) continue;
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

    @Override
    public Double getMoviePrScore(BoxCalculateVo boxCalculateVo) {
        List<Integer> actors = boxCalculateVo.getActors();
        List<Integer> directors = boxCalculateVo.getDirectors();
        List<Integer> scenarists = boxCalculateVo.getScenarists();

        Session session = Neo4jUtil.getSession();
        Double res = 0.15002;
        Result result = null;

        //check is exists graph
        boolean isGraphExists = false;
        try {
            result = session.run("CALL gds.graph.exists('my-graph') YIELD exists;");
        } catch (Exception e) {
            e.printStackTrace();
            return res;
        }
        //if exists, drop it
        while (result.hasNext()) {
            Record record = result.next();
            isGraphExists = record.get("exists").asBoolean();
        }
        if (isGraphExists) {
            session.run("CALL gds.graph.drop('my-graph') YIELD graphName;");
        }

        //add node
        Set<Integer> peopleSet = new HashSet<>();
        if (actors != null) peopleSet.addAll(actors);
        if (directors != null) peopleSet.addAll(directors);
        if (scenarists != null) peopleSet.addAll(scenarists);
        List<Integer> peopleList = new ArrayList<>(peopleSet);

        String personTemp = "(p%d:person {uid: '%d'}),";
        StringBuffer personStr = new StringBuffer();
        for (int i = 0; i < peopleList.size(); i++) {
            personStr.append(String.format(personTemp, i, peopleList.get(i)));
        }

        String relationTemp = "create (m)-[:ACT{weight:0}]->(p%d)\n";
        StringBuffer relationStr = new StringBuffer();
        for (int i = 0; i < peopleList.size(); i++) {
            relationStr.append(String.format(relationTemp, i));
        }
        StringBuffer cqlSb = new StringBuffer();
        cqlSb.append("create (m:movie{mid:'xx'})\n");
        if (peopleList.size() > 0) {
            cqlSb.insert(0, String.format("match %s\n", personStr.deleteCharAt(personStr.length() - 1).toString()));
            cqlSb.append(String.format("%s", relationStr.toString()));
        }
        String cql = cqlSb.toString();
        System.out.println(cql);
        session.run(cql);

        //run pageRank
        cql = "CALL gds.alpha.closeness.stream({\n" +
                "  nodeQuery: 'MATCH (p) RETURN id(p) AS id',\n" +
                "  relationshipQuery: 'MATCH (p1)-[]-(p2) RETURN id(p1) AS source, id(p2) AS target'\n" +
                "}) YIELD nodeId, centrality WHERE gds.util.asNode(nodeId).mid = 'xx'\n" +
                "RETURN gds.util.asNode(nodeId).mid AS id, centrality\n" +
                "ORDER BY centrality DESC";
        result = session.run(cql);
        while (result.hasNext()) {
            Record record = result.next();
            res = record.get("centrality").asDouble();
            System.out.println("centrality = " + res);
        }

        //del node
        cql = "match (m:movie{mid:'xx'})-[r]-()\n" +
                "delete r,m";
        session.run(cql);


        return res;
    }


}
