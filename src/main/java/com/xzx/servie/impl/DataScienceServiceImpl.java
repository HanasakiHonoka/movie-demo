package com.xzx.servie.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzx.constant.ConstantParam;
import com.xzx.constant.MovieTypeEnum;
import com.xzx.dto.BoxCalculateDto;
import com.xzx.entity.Actor;
import com.xzx.entity.Director;
import com.xzx.entity.Scenarist;
import com.xzx.entity.Task;
import com.xzx.servie.*;
import com.xzx.util.DataScienceUtil;
import com.xzx.util.Neo4jUtil;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import com.xzx.vo.RecommendParamVo;
import com.xzx.vo.RecommendVo;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataScienceServiceImpl implements DataScienceService {

    @Value("${dataScienceServer}")
    private String dataScienceServer;

    @Autowired
    private IActorService actorService;
    @Autowired
    private IDirectorService directorService;
    @Autowired
    private IScenaristService scenaristService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private Neo4jService neo4jService;

    @Override
    @Async
    public void boxCalculate(BoxCalculateDto boxCalculateDto) {

        /*
        {
    "m_type": ["戏剧"],
    "m_runtime": 90,
    "m_dic": [],
    "is_ip": "是",
    "is_follow": "是",
    "m_budget": "500万以下",
    "m_tech": "2D",
    "m_act": [],
    "m_sci": [],
    "m_date": "2020-07-05T16:00:00.000Z"
}

         */
        BoxCalculateVo boxCalculateVo = boxCalculateDto.getBoxCalculateVo();

        Task task = new Task();
        task.setId(boxCalculateDto.getId());
        task.setRequest(JSON.toJSONString(boxCalculateVo));
        task.setStatus(ConstantParam.INIT_STATUS);
        task.setStartTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        taskService.save(task);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        boolean useNeo4j = boxCalculateVo.isUseNeo4j();
        JSONObject pyReqParam = DataScienceUtil.getDefaultPyReqParam(useNeo4j);

        log.info("boxCalculateVo = " + boxCalculateVo.toString());

        //isIp, isSequel
        if (boxCalculateVo.getIsIp() != null) {
            pyReqParam.put("is_ip", boxCalculateVo.getIsIp() ? 1 : 0);
        }
        if (boxCalculateVo.getIsSequel() != null) {
            pyReqParam.put("is_sequel", boxCalculateVo.getIsSequel() ? 1 : 0);
        }

        //neo pr rank
        if (useNeo4j) {
            //TODO neo4j
            task.setStatus(ConstantParam.RUNNING_STATUS);
            task.setUpdateTime(LocalDateTime.now());
            taskService.updateById(task);
            pyReqParam.put("pr_box_rank", neo4jService.getMoviePrScore(boxCalculateVo));
        }

        //type
        if (boxCalculateVo.getType() != null && boxCalculateVo.getType().size() > 0) {
            int successType = 0, type_sum = 0;
            for (String type : boxCalculateVo.getType()) {
                if (MovieTypeEnum.getByRawName(type) == null) continue;
                type_sum += MovieTypeEnum.getByRawName(type).getScore();
                successType++;
            }
            pyReqParam.put("num_types", successType);
            pyReqParam.put("genre_avg", type_sum / successType);
        }

        //date
        if (boxCalculateVo.getReleaseTime() != null) {
            DateTime dateTime = DateTime.of(boxCalculateVo.getReleaseTime());
            int year = dateTime.year();
            int month = dateTime.monthStartFromOne();
            int day = dateTime.dayOfMonth();
            int dayOfWeek = dateTime.dayOfWeek();
            int quarter = dateTime.quarter();
            int week = dateTime.weekOfYear();
            pyReqParam.put("year", year);
            pyReqParam.put("month", month);
            //pyReqParam.put("day", day);
            //pyReqParam.put("day_of_week", dayOfWeek);
            pyReqParam.put("quarter", quarter);
            if (dayOfWeek == 1 || dayOfWeek == 7) {
                pyReqParam.put("is_weekend", 1);
            }
            pyReqParam.put("year_mean_1000", DataScienceUtil.getYearMean(year));
            if ((month == 1 && day > 20) ||(month == 2 && day <= 20)) {
                pyReqParam.put("springfestival", true);
            }
            if (month == 7 || month == 8) {
                pyReqParam.put("summer", true);
            }

            //pyReqParam.put("week", week);
        }

        //duration
        if (boxCalculateVo.getDuration() != null) {
            Integer duration = boxCalculateVo.getDuration();
            if (duration < 85) {
                pyReqParam.put("duration_85", true);
            } else if (duration < 140) {
                pyReqParam.put("duration_85_140", true);
            } else {
                pyReqParam.put("duration_140", true);
            }
        }

        //tech
        if (boxCalculateVo.getTechnology() != null) {
            switch (boxCalculateVo.getTechnology()) {
                case "2D":
                    pyReqParam.put("technology_2D", 1);
                    break;
                case "3D":
                    pyReqParam.put("technology_3D", 1);
                    break;
                case "IMAX":
                    pyReqParam.put("technology_IMAX", 1);
                    break;
            }
        }

        //directors
        if (boxCalculateVo.getDirectors() != null && boxCalculateVo.getDirectors().size() > 0) {
            Director director1 = directorService.getById(boxCalculateVo.getDirectors().get(0));
            pyReqParam.put("director_boxoffice_average", director1.getBoxoffice() / director1.getAmount());
            pyReqParam.put("director_amount", director1.getAmount());
            if (useNeo4j) pyReqParam.put("director_1_pr_boxrank", director1.getPrBoxRank());
            if (boxCalculateVo.getDirectors().size() > 1) {
                Director director2 = directorService.getById(boxCalculateVo.getDirectors().get(1));
                if (director2.getAmount() > 0) {
                    pyReqParam.put("has_2_director", true);
                    if (useNeo4j) pyReqParam.put("director_2_pr_boxrank", director2.getPrBoxRank());
                }
            }
                //pyReqParam.put("director_" + (i + 1) + "_amount", director.getMovieAmount1());
                //pyReqParam.put("director_" + (i + 1) + "_boxoffice", director.getBoxofficeAmount1());
        }

        //actors
        if (boxCalculateVo.getActors() != null && boxCalculateVo.getActors().size() > 0) {
            Actor actor1 = actorService.getById(boxCalculateVo.getActors().get(0));
            pyReqParam.put("actor_1_boxoffice_average", actor1.getBoxoffice() / actor1.getAmount());
            pyReqParam.put("actor_amount", actor1.getAmount());
            if (useNeo4j) pyReqParam.put("actor_1_pr_boxrank", actor1.getPrBoxRank());
            if (boxCalculateVo.getActors().size() > 1) {
                Actor actor2 = actorService.getById(boxCalculateVo.getActors().get(1));
                pyReqParam.put("actor_2_boxoffice_average", actor2.getBoxoffice() / actor2.getAmount());
                pyReqParam.put("actor_amount", actor1.getAmount() + actor2.getAmount());
                if (useNeo4j) pyReqParam.put("actor_2_pr_boxrank", actor2.getPrBoxRank());
            }
        }

        //scenarists
        if (boxCalculateVo.getScenarists() != null && boxCalculateVo.getScenarists().size() > 0) {
            Scenarist scenarist1 = scenaristService.getById(boxCalculateVo.getScenarists().get(0));
            pyReqParam.put("scenarist_boxoffice_average", scenarist1.getBoxoffice() / scenarist1.getAmount());
            pyReqParam.put("scenarist_amount", scenarist1.getAmount());
            if (useNeo4j) pyReqParam.put("scenarist_1_pr_boxrank", scenarist1.getPrBoxRank());
            if (boxCalculateVo.getScenarists().size() > 1) {
                Scenarist scenarist2 = scenaristService.getById(boxCalculateVo.getScenarists().get(1));
                if (scenarist2.getAmount() > 0) {
                    pyReqParam.put("has_2_scenarist", true);
                    if (useNeo4j) pyReqParam.put("scenarist_2_pr_boxrank", scenarist2.getPrBoxRank());
                }
            }
        }

        //star issue
        if (boxCalculateVo.getIssueId() != null) {
            if (DataScienceUtil.isStarIssue(boxCalculateVo.getIssueId())) {
                pyReqParam.put("is_star_issue_company", true);
            }
        }

        //star manu
        if (boxCalculateVo.getManuId() != null) {
            if (DataScienceUtil.isStarManu(boxCalculateVo.getManuId())) {
                pyReqParam.put("is_star_manu_company", true);
            }
        }

        log.info("pyPeqParam = " + pyReqParam.toJSONString());

        JSONObject sendToPy = new JSONObject();
        sendToPy.put("1", pyReqParam);
        String api = "";

        if (!useNeo4j) {
            api = "/data/box_value";
        } else {
            api = "/data/box_value_neo";
        }
        //String res = "11";
        String res = HttpRequest.post(dataScienceServer + api).body(sendToPy.toJSONString()).execute().body();
        System.out.println(res);


        if(res != null) {
            BoxResVo boxResVo = JSON.parseObject(res).toJavaObject(BoxResVo.class);
            task.setResponse(JSON.toJSONString(boxResVo));
            task.setStatus(ConstantParam.SUCCESS_STATUS);
            task.setUpdateTime(LocalDateTime.now());
            taskService.updateById(task);
        } else {
            task.setStatus(ConstantParam.ERR_STATUS);
            task.setUpdateTime(LocalDateTime.now());
            taskService.updateById(task);
        }
    }

    @Override
    public List<RecommendVo> characterRecommend(RecommendParamVo recommendParamVo) {
        Session session = Neo4jUtil.getSession();

        List<Integer> personIdList = new ArrayList<>();
        if (recommendParamVo.getDirectorId() != null) {
            personIdList.addAll(recommendParamVo.getDirectorId());
        }
        if (recommendParamVo.getScenaristId() != null) {
            personIdList.addAll(recommendParamVo.getScenaristId());
        }
        if (personIdList.size() < 1) {
            return null;
        }
        //personIdList.add("1274331");
        //personIdList.add("1054424");
        String personTemp = "(p%d:person {person_id: '%d'}),";
        StringBuffer personStr = new StringBuffer();
        for (int i = 0; i < personIdList.size(); i++) {
            personStr.append(String.format(personTemp, i, personIdList.get(i)));
        }
        String s1 = personStr.deleteCharAt(personStr.length() - 1).toString();

        StringBuffer personList = new StringBuffer();
        for (int i = 0; i < personIdList.size(); i++) {
            personList.append("p" + i + ",");
        }
        String s2 = personList.deleteCharAt(personList.length() - 1).toString();

        String cql = String.format("MATCH %s\n" +
                "CALL gds.pageRank.stream('graph-person', {\n" +
                "  maxIterations: 20,\n" +
                "  dampingFactor: 0.85,\n" +
                "  relationshipWeightProperty: 'weight',\n" +
                "  sourceNodes: [%s]\n" +
                "})\n" +
                "YIELD nodeId, score\n" +
                "where gds.util.asNode(nodeId).person_name is not null\n" +
                "with gds.util.asNode(nodeId) as n, score\n" + 
                "match (n)-[r]->(m) where r.relation='ACTrelation'\n" +
                "return distinct n.person_name as person_name,  n.person_id as person_id, score " +
                "ORDER BY score DESC", s1, s2);
        log.info("cql=" + cql);
        Result result = null;


        if (true) {
            InputStream is = this.getClass().getResourceAsStream("/chaRecommendRes.json");
            String s = IoUtil.read(is, StandardCharsets.UTF_8);
            JSONArray jsonArray = JSONObject.parseArray(s);
            System.out.println(jsonArray);
            return jsonArray.toJavaList(RecommendVo.class);
        }
        try {
            result = session.run(cql);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        int count = 0;
        List<RecommendVo> res = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            RecommendVo recommendVo = new RecommendVo();
            recommendVo.setId(record.get("person_id").toString().replaceAll("\"", ""));
            recommendVo.setName(record.get("person_name").toString().replaceAll("\"", ""));
            recommendVo.setScore(record.get("score").toString().replaceAll("\"", ""));
            res.add(recommendVo);
            count++;
            if (count > recommendParamVo.getPeopleNum()) break;
        }

        return res;
    }
}
