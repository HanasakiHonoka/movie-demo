package com.xzx.servie.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzx.constant.MovieTypeEnum;
import com.xzx.servie.DataScienceService;
import com.xzx.util.DataScienceUtil;
import com.xzx.vo.BoxCalculateVo;
import com.xzx.vo.BoxResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Set;

@Slf4j
@Service
public class DataScienceServiceImpl implements DataScienceService {

    @Value("${dataScienceServer}")
    private String dataScienceServer;

    @Override
    public BoxResVo boxCalculate(BoxCalculateVo boxCalculateVo) {

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

        boolean isPeople = false;
        if (boxCalculateVo.getActors() != null || boxCalculateVo.getDirectors() != null || boxCalculateVo.getScenarists() != null) isPeople = true;
        JSONObject pyReqParam = DataScienceUtil.getDefaultPyReqParam(isPeople);

        log.info("boxCalculateVo = " + boxCalculateVo.toString());

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
        log.info("pyPeqParam = " + pyReqParam.toJSONString());

        JSONObject sendToPy = new JSONObject();
        sendToPy.put("1", pyReqParam);
        String api = "";

        if (!isPeople) {
            api = "/data/box_value";
        } else {
            api = "/data/box_value_person";
        }
        String res = HttpRequest.post(dataScienceServer + api).body(sendToPy.toJSONString()).execute().body();


        BoxResVo boxResVo = new BoxResVo();
        //if(res != null) {
        //    Map resMap = JSON.parseObject(res, Map.class);
        //    Object boxValue = resMap.get("boxValue");
        //    System.out.println(boxValue);
        //    int intBoxValue = 0;
        //    try {
        //        intBoxValue = Integer.parseInt((String) boxValue);
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //    if (intBoxValue < 0) {
        //        intBoxValue = -intBoxValue;
        //    }
        //    boxResVo.setBoxValue(intBoxValue);
        //}else {
        //    boxResVo.setBoxValue(0);
        //}
        boxResVo.setBoxValue(Integer.parseInt(res));
        return boxResVo;
    }
}
