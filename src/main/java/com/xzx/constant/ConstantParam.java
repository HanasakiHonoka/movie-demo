package com.xzx.constant;

/**
 * @ClassName ConstatParam
 * @Description
 * @Author XZX
 * @Date 2020/7/19 14:38
 * @Version V1.0
 **/
public class ConstantParam {

    public final static int DEFAULT_PAGE_SIZE = 10;

    //以电影为中心的各个关联节点数量默认值

    public final static int DEFAULT_MOVIE_DIRECTOR_NUM = 1;
    public final static int DEFAULT_MOVIE_ACTOR_NUM = 6;
    public final static int DEFAULT_MOVIE_SCENARIST_NUM = 1;

    //以人物为中心的各个关联节点数量默认值
    //第一层关系
    public final static int DEFAULT_PERSON_DIRECTOR_NUM = 1;
    public final static int DEFAULT_PERSON_ACTOR_NUM = 6;
    public final static int DEFAULT_PERSON_SCENARIST_NUM = 1;
    //第二层关系
    public final static int DEFAULT_M_PERSON_DIRECTOR_NUM = 1;
    public final static int DEFAULT_M_PERSON_ACTOR_NUM = 1;
    public final static int DEFAULT_M_PERSON_SCENARIST_NUM = 1;
}
