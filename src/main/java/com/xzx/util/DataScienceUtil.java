package com.xzx.util;

import com.alibaba.fastjson.JSONObject;

public class DataScienceUtil {

    public static JSONObject getDefaultPyReqParam(boolean isPeople) {
        JSONObject defaultPyReqRaram = new JSONObject(true);
        defaultPyReqRaram.put("duration", 0);
        if (isPeople) {
            defaultPyReqRaram.put("director_1_amount", 0);
            defaultPyReqRaram.put("director_1_boxoffice", 0);
            defaultPyReqRaram.put("director_2_amount", 0);
            defaultPyReqRaram.put("director_2_boxoffice", 0);

            defaultPyReqRaram.put("scenarist_1_amount", 0);
            defaultPyReqRaram.put("scenarist_1_boxoffice", 0);
            defaultPyReqRaram.put("scenarist_2_amount", 0);
            defaultPyReqRaram.put("scenarist_2_boxoffice", 0);

            defaultPyReqRaram.put("actor_1_amount", 0);
            defaultPyReqRaram.put("actor_1_boxoffice", 0);
            defaultPyReqRaram.put("actor_2_amount", 0);
            defaultPyReqRaram.put("actor_2_boxoffice", 0);
        }
        defaultPyReqRaram.put("year", 0);
        defaultPyReqRaram.put("month", 0);
        defaultPyReqRaram.put("day", 0);
        defaultPyReqRaram.put("day_of_week", 0);
        defaultPyReqRaram.put("quarter", 0);
        defaultPyReqRaram.put("week", 0);
        defaultPyReqRaram.put("num_types", 0);
        defaultPyReqRaram.put("genre_剧情", 0);
        defaultPyReqRaram.put("genre_爱情", 0);
        defaultPyReqRaram.put("genre_喜剧", 0);
        defaultPyReqRaram.put("genre_动作", 0);
        defaultPyReqRaram.put("genre_惊悚", 0);
        defaultPyReqRaram.put("genre_动画", 0);
        defaultPyReqRaram.put("genre_悬疑", 0);
        defaultPyReqRaram.put("genre_冒险", 0);
        defaultPyReqRaram.put("genre_犯罪", 0);
        defaultPyReqRaram.put("genre_战争", 0);
        defaultPyReqRaram.put("genre_恐怖", 0);
        defaultPyReqRaram.put("genre_奇幻", 0);
        defaultPyReqRaram.put("genre_儿童", 0);
        defaultPyReqRaram.put("genre_纪录片", 0);
        defaultPyReqRaram.put("genre_青春", 0);
        defaultPyReqRaram.put("technology_2D", 0);
        defaultPyReqRaram.put("technology_3D", 0);
        defaultPyReqRaram.put("technology_IMAX", 0);
        return defaultPyReqRaram;
    }
}
