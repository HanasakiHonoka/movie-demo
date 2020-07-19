package com.xzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
