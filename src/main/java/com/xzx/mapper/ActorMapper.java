package com.xzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.dto.PeopleWithBox;
import com.xzx.entity.Actor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Component
public interface ActorMapper extends BaseMapper<Actor> {

    @Select("select a.* from actor_participate ap, actor_table a where ap.movie_id = #{movieId} and ap.actor_id = a.id")
    List<Actor> getActorByMovieId(@Param("movieId") Integer movieId);

    @Select("select a.* from actor_participate_notshown ap, actor_table a where ap.movie_id = #{movieId} and ap.actor_id = a.id")
    List<Actor> getNotShownActorByMovieId(@Param("movieId") Integer movieId);

    @Select("select a.id, a.`name`, a.boxoffice, a.amount from actor_table a " +
            "where a.boxoffice is NOT NULL and a.amount is not null " +
            "ORDER BY boxoffice DESC limit 10")
    List<PeopleWithBox> getTopActorWithBox();

    @Select("select a.id, a.`name`, a.boxoffice, a.amount from actor_table a " +
            "where a.boxoffice is NOT NULL and a.amount is not null and a.gender = '女' " +
            "ORDER BY boxoffice DESC limit 10")
    List<PeopleWithBox> getTopFemaleActorWithBox();

    @Select("select a.id, a.`name`, a.boxoffice, a.amount from actor_table a " +
            "where a.boxoffice is NOT NULL and a.amount is not null and a.gender = '男'" +
            "ORDER BY boxoffice DESC limit 10")
    List<PeopleWithBox> getTopMaleActorWithBox();
}
