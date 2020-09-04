package com.xzx.constant;

/**
 * @ClassName ConstatParam
 * @Description
 * @Author XZX
 * @Date 2020/7/19 14:38
 * @Version V1.0
 **/
public class ConstantParam {

    //每页默认记录数量
    public final static int DEFAULT_PAGE_SIZE = 10;

    public final static int DEFAULT_MOVIE_DIRECTOR_NUM = 2;
    public final static int DEFAULT_MOVIE_ACTOR_NUM = 5;
    public final static int DEFAULT_MOVIE_SCENARIST_NUM = 3;

    public final static int DEFAULT_PERSON_DIRECTOR_NUM = 5;
    public final static int DEFAULT_PERSON_ACTOR_NUM = 5;
    public final static int DEFAULT_PERSON_SCENARIST_NUM = 5;

    //数据分析任务状态
    public final static int DEFAULT_STATUS = 0;
    public final static int INIT_STATUS = -1;
    public final static int RUNNING_STATUS = -2;
    public final static int ERR_STATUS = -3;
    public final static int SUCCESS_STATUS = 1;



    //Neo4j 部分
    //以电影为中心的各个关联节点数量默认值
    public final static int NEO_DEFAULT_MOVIE_DIRECTOR_NUM = 1;
    public final static int NEO_DEFAULT_MOVIE_ACTOR_NUM = 6;
    public final static int NEO_DEFAULT_MOVIE_SCENARIST_NUM = 1;

    //以人物为中心的各个关联节点数量默认值
    //第一层关系
    public final static int NEO_DEFAULT_PERSON_DIRECTOR_NUM = 1;
    public final static int NEO_DEFAULT_PERSON_ACTOR_NUM = 6;
    public final static int NEO_DEFAULT_PERSON_SCENARIST_NUM = 1;
    //第二层关系
    public final static int NEO_DEFAULT_M_PERSON_DIRECTOR_NUM = 1;
    public final static int NEO_DEFAULT_M_PERSON_ACTOR_NUM = 1;
    public final static int NEO_DEFAULT_M_PERSON_SCENARIST_NUM = 1;

    //角色推荐默认返回的候选人物数量
    public final static int RECOMMEND_PEOPLE_NUM = 10;

}
