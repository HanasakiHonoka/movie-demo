package com.xzx.util;

import com.alibaba.fastjson.JSONObject;
import com.xzx.constant.ConstantParam;
import io.swagger.models.auth.In;

public class DataScienceUtil {

    public static JSONObject getDefaultPyReqParam(boolean useNeo4j) {
        JSONObject defaultPyReqRaram = new JSONObject(true);
        if (useNeo4j) defaultPyReqRaram.put("pr_box_rank", 0);
        defaultPyReqRaram.put("is_ip", 0);
        defaultPyReqRaram.put("is_sequel", 0);
        if (useNeo4j) {
            defaultPyReqRaram.put("director_1_pr_boxrank", 0);
            defaultPyReqRaram.put("director_2_pr_boxrank", 0);
            defaultPyReqRaram.put("actor_1_pr_boxrank", 0);
            defaultPyReqRaram.put("actor_2_pr_boxrank", 0);
            defaultPyReqRaram.put("scenarist_1_pr_boxrank", 0);
            defaultPyReqRaram.put("scenarist_2_pr_boxrank", 0);
        }

        defaultPyReqRaram.put("year", 0);
        defaultPyReqRaram.put("month", 0);
        defaultPyReqRaram.put("quarter", 0);

        defaultPyReqRaram.put("duration_85", false);
        defaultPyReqRaram.put("duration_85_140", false);
        defaultPyReqRaram.put("duration_140", false);

        defaultPyReqRaram.put("num_types", 0);
        defaultPyReqRaram.put("genre_avg", 0);

        defaultPyReqRaram.put("technology_2D", 0);
        defaultPyReqRaram.put("technology_3D", 0);
        defaultPyReqRaram.put("technology_IMAX", 0);

        defaultPyReqRaram.put("year_mean_1000", 0);
        defaultPyReqRaram.put("is_weekend", 0);
        defaultPyReqRaram.put("springfestival", false);
        defaultPyReqRaram.put("summer", false);

        defaultPyReqRaram.put("is_star_issue_company", false);
        defaultPyReqRaram.put("is_star_manu_company", false);

        defaultPyReqRaram.put("has_2_director", false);
        defaultPyReqRaram.put("has_2_scenarist", false);

        defaultPyReqRaram.put("director_boxoffice_average", 0);
        defaultPyReqRaram.put("scenarist_boxoffice_average", 0);
        defaultPyReqRaram.put("actor_1_boxoffice_average", 0);
        defaultPyReqRaram.put("actor_2_boxoffice_average", 0);
        defaultPyReqRaram.put("director_amount", 0);
        defaultPyReqRaram.put("scenarist_amount", 0);
        defaultPyReqRaram.put("actor_amount", 0);

        return defaultPyReqRaram;
    }

    public static Integer getYearMean(Integer year) {
        switch (year) {
            case 2000:return 7229;
            case 2001:return 3300;
            case 2002:return 9600;
            case 2003:return 3168;
            case 2004:return 7346;
            case 2005:return 2842;
            case 2006:return 7689;
            case 2007:return 5170;
            case 2008:return 6437;
            case 2009:return 7914;
            case 2010:return 9590;
            case 2011:return 9441;
            case 2012:return 11060;
            case 2013:return 16769;
            case 2014:return 16758;
            case 2015:return 24064;
            case 2016:return 24832;
            case 2017:return 27643;
            case 2018:return 40718;
            case 2019:return 38984;
        }
        return 38984;
    }

    public static boolean isStarIssue(Integer id) {
        int[] starList = new int[]{1, 59, 80, 118, 187, 257, 262, 593, 639, 659, 808, 883, 1005, 1119, 1201, 1488, 1774, 1793, 2153, 2156, 2703, 2709, 4199,4224,5101};
        for (int i : starList) {
            if (i == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStarManu(Integer id) {
        int[] starList = new int[]{59, 63, 66, 67, 74, 80, 118, 167, 187, 257, 306, 311, 407, 593, 639, 871, 877, 881, 883, 1004, 1005, 1201, 1257, 1356, 1559, 1759, 1793, 1847, 2158, 2160, 2166, 2187, 2216, 2708, 2709, 2710, 2799, 2866, 2970, 4208};
        for (int i : starList) {
            if (i == id) {
                return true;
            }
        }
        return false;
    }

    public static String getMsgByStatus(Integer id) {
        switch (id) {
            case ConstantParam.INIT_STATUS: return "初始化";
            case ConstantParam.RUNNING_STATUS: return "运行中";
            case ConstantParam.ERR_STATUS: return "错误";
            case ConstantParam.SUCCESS_STATUS: return "成功";
        }
        return "未知";
    }
}
/*
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

        defaultPyReqRaram.put("duration", 0);
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

        defaultPyReqRaram.put("day", 0);
        defaultPyReqRaram.put("day_of_week", 0);

        defaultPyReqRaram.put("week", 0);
 */