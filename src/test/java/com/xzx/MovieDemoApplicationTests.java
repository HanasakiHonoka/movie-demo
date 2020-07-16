package com.xzx;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzx.constant.MovieTypeEnum;
import com.xzx.entity.Director;
import com.xzx.entity.Movie;
import com.xzx.entity.MovieExample;
import com.xzx.mapper.MovieMapper;
import com.xzx.servie.ActorService;
import com.xzx.servie.DirectorService;
import com.xzx.servie.MovieService;
import com.xzx.servie.ScenaristService;
import com.xzx.util.DataScienceUtil;
import com.xzx.vo.BoxCalculateVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
class MovieDemoApplicationTests {

    @Value("${dataScienceServer}")
    private String dataScienceServer;

    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private ScenaristService scenaristService;

    @Test
    void contextLoads() {
    }

    //@Test
    //public void test01() {
    //    MovieExample example = new MovieExample();
    //    MovieExample.Criteria criteria = example.createCriteria();
    //    criteria.andTitleLike("%战%");
    //    List<Movie> movie1s = movieMapper.selectByExample(example);
    //
    //    System.out.println("size" + movie1s.size());
    //    for (Movie movie:movie1s) {
    //        System.out.println(movie.getTitle());
    //    }
    //}

    @Test
    public void testn4o4j() {
        Driver driver = GraphDatabase.driver("bolt://106.54.68.249:7787", AuthTokens.basic("neo4j", "movie@159357"));
    }

    @Test
    public void dsTest() {
        String fro_json = "{\n" +
                "    \"m_type\": [\"戏剧\", \"剧情\", \"爱情\"],\n" +
                "    \"m_runtime\": 90,\n" +
                "    \"m_dic\": [1000525],\n" +
                "    \"is_ip\": \"是\",\n" +
                "    \"is_follow\": \"是\",\n" +
                "    \"m_budget\": \"500万以下\",\n" +
                "    \"m_tech\": \"2D\",\n" +
                "    \"m_act\": [],\n" +
                "    \"m_sci\": [],\n" +
                "    \"m_date\": \"2020-10-02T16:00:00.000Z\"\n" +
                "}";
        JSONObject req_parm = JSON.parseObject(fro_json);
        //pyReqParam
        Set<String> keySet = req_parm.keySet();
        boolean isPeople = false;
        if (keySet.contains("m_dic") && req_parm.getJSONArray("m_dic").size() > 0) isPeople = true;
        if (keySet.contains("m_act") && req_parm.getJSONArray("m_act").size() > 0) isPeople = true;
        if (keySet.contains("m_sci") && req_parm.getJSONArray("m_sci").size() > 0) isPeople = true;
        JSONObject pyReqParam = DataScienceUtil.getDefaultPyReqParam(isPeople);
        for (String key: keySet) {
            if (key.equals("m_type")) {
                JSONArray typeArray = req_parm.getJSONArray("m_type");
                if (typeArray.size() < 1) continue;
                int successType = 0;
                for (int i = 0; i < typeArray.size(); i++) {
                    String type = typeArray.getString(i);
                    if (MovieTypeEnum.getByRaWName(type) == null) continue;
                    pyReqParam.put(MovieTypeEnum.getByRaWName(type).getParamName(), 1);
                    successType++;
                }
                pyReqParam.put("num_types", successType);
            } else if (key.equals("m_dic")) {
                JSONArray mDic = req_parm.getJSONArray("m_dic");
                if (mDic.size() > 0) {
                    Integer[] mDicArray = mDic.toArray(new Integer[0]);
                    for (int i = 0; i < mDicArray.length && i < 2; i++) {
                        //Director director = directorService.getDirector(mDicArray[i]);
                        if (i == 0) {
                            pyReqParam.put("director_1_amount", 0);
                            pyReqParam.put("director_1_boxoffice", 0);
                        } else if (i == 1){
                            pyReqParam.put("director_2_amount", 0);
                            pyReqParam.put("director_2_boxoffice", 0);
                        }
                    }
                }
            } else if (key.equals("m_sci")) {
                JSONArray mSci = req_parm.getJSONArray("m_sci");
                if (mSci.size() > 0) {
                    Integer[] mSciArray = mSci.toArray(new Integer[0]);
                    for (int i = 0; i < mSciArray.length && i < 2; i++) {
                        if (i == 0) {
                            pyReqParam.put("scenarist_1_amount", 0);
                            pyReqParam.put("scenarist_1_boxoffice", 0);
                        } else if (i == 1){
                            pyReqParam.put("scenarist_2_amount", 0);
                            pyReqParam.put("scenarist_2_boxoffice", 0);
                        }
                    }
                }
            } else if (key.equals("m_act")) {
                JSONArray mAct = req_parm.getJSONArray("m_act");
                if (mAct.size() > 0) {
                    Integer[] mActArray = mAct.toArray(new Integer[0]);
                    for (int i = 0; i < mActArray.length && i < 2; i++) {
                        if (i == 0) {
                            pyReqParam.put("actor_1_amount", 0);
                            pyReqParam.put("actor_1_boxoffice", 0);
                        } else if (i == 1){
                            pyReqParam.put("actor_2_amount", 0);
                            pyReqParam.put("actor_2_boxoffice", 0);
                        }
                    }
                }
            } else if (key.equals("m_date")) {
                String dateStr = req_parm.getString("m_date");
                DateTime dateTime = DateUtil.parse(dateStr, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                int year = dateTime.year();
                int month = dateTime.monthStartFromOne();
                int day = dateTime.dayOfMonth();
                int dayOfWeek = dateTime.dayOfWeek();
                int quarter = dateTime.quarter();
                int week = dateTime.weekOfYear();
                pyReqParam.put("year", year);
                pyReqParam.put("month", month);
                pyReqParam.put("day", day);
                pyReqParam.put("day_of_week", dayOfWeek);
                pyReqParam.put("quarter", quarter);
                pyReqParam.put("week", week);
            } else if (key.equals("m_runtime")) {
                pyReqParam.put("duration", Integer.parseInt(req_parm.getString("m_runtime")));
            } else if (key.equals("m_tech")) {
                switch (req_parm.getString("m_tech")) {
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
        }
        System.out.println(pyReqParam);
        JSONObject sendToPy = new JSONObject();
        sendToPy.put("1", pyReqParam);

        //String res = HttpRequest.post(dataScienceServer + "/data/box_test").body(sendToPy.toJSONString()).execute().body();
        String res = HttpRequest.post(dataScienceServer + "/data/box_value_person").body(sendToPy.toJSONString()).execute().body();
        System.out.println(res);
    }

    @Test
    public void objectTest() {
        //BoxCalculateVo(type=[剧情, 爱情], duration=120, releaseTime=Thu Oct 01 00:00:00 CST 2020, isIp=false, isSequel=false, budget=1, technology=2D, directors=[10031], actors=[10031], scenarists=[10031])
        String s = "{\"actors\":[1000525],\"budget\":\"1\",\"directors\":[1000525],\"duration\":120,\"isIp\":false,\"isSequel\":false,\"releaseTime\":1601481600000,\"scenarists\":[1000525],\"technology\":\"2D\",\"type\":[\"剧情\",\"爱情\"]}";
        BoxCalculateVo boxCalculateVo = JSONObject.parseObject(s).toJavaObject(BoxCalculateVo.class);
        boolean isPeople = false;
        if (boxCalculateVo.getActors() != null || boxCalculateVo.getDirectors() != null || boxCalculateVo.getScenarists() != null) isPeople = true;
        JSONObject pyReqParam = DataScienceUtil.getDefaultPyReqParam(isPeople);

        //type
        if (boxCalculateVo.getType() != null &&  boxCalculateVo.getType().size() > 0) {
            int successType = 0;
            for (String type: boxCalculateVo.getType()) {
                if (MovieTypeEnum.getByRaWName(type) == null) continue;
                pyReqParam.put(MovieTypeEnum.getByRaWName(type).getParamName(), 1);
                successType++;
            }
            pyReqParam.put("num_types", successType);
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
            pyReqParam.put("day", day);
            pyReqParam.put("day_of_week", dayOfWeek);
            pyReqParam.put("quarter", quarter);
            pyReqParam.put("week", week);
        }
        
        //duration
        if (boxCalculateVo.getDuration() != null) {
            pyReqParam.put("duration", boxCalculateVo.getDuration());
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
            for (int i = 0; i < boxCalculateVo.getDirectors().size() && i < 2; i++) {
                if (i == 0) {
                    pyReqParam.put("director_1_amount", 0);
                    pyReqParam.put("director_1_boxoffice", 0);
                } else if (i == 1){
                    pyReqParam.put("director_2_amount", 0);
                    pyReqParam.put("director_2_boxoffice", 0);
                }
            }
        }

        //actors
        if (boxCalculateVo.getActors() != null && boxCalculateVo.getActors().size() > 0) {
            for (int i = 0; i < boxCalculateVo.getActors().size() && i < 2; i++) {
                if (i == 0) {
                    pyReqParam.put("actor_1_amount", 0);
                    pyReqParam.put("actor_1_boxoffice", 0);
                } else if (i == 1){
                    pyReqParam.put("actor_2_amount", 0);
                    pyReqParam.put("actor_2_boxoffice", 0);
                }
            }
        }

        //scenarists
        if (boxCalculateVo.getScenarists() != null && boxCalculateVo.getScenarists().size() > 0) {
            for (int i = 0; i < boxCalculateVo.getScenarists().size() && i < 2; i++) {
                if (i == 0) {
                    pyReqParam.put("scenarist_1_amount", 0);
                    pyReqParam.put("scenarist_1_boxoffice", 0);
                } else if (i == 1){
                    pyReqParam.put("scenarist_2_amount", 0);
                    pyReqParam.put("scenarist_2_boxoffice", 0);
                }
            }
        }
        JSONObject sendToPy = new JSONObject();
        sendToPy.put("1", pyReqParam);
        String res = HttpRequest.post(dataScienceServer + "/data/box_value_person").body(sendToPy.toJSONString()).execute().body();
        System.out.println(res);
    }

}
