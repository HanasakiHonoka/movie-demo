package com.xzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.dto.YearBoxOffice;
import com.xzx.entity.Movie;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Repository
public interface MovieMapper extends BaseMapper<Movie> {

    @Select("select m.* from actor_participate ap, movie_table m where ap.actor_id = #{actorId} and ap.movie_id = m.id")
    List<Movie> getMovieByActorId(@Param("actorId") Integer actorId);

    @Select("select m.* from d_s_participate dsp, movie_table m where dsp.person_occu = 1 and dsp.person_id = #{directorId} and dsp.movie_id = m.id")
    List<Movie> getMovieByDirectorId(@Param("directorId") Integer directorId);

    @Select("select m.* from d_s_participate dsp, movie_table m where dsp.person_occu = 2 and dsp.person_id = #{scenaristId} and dsp.movie_id = m.id")
    List<Movie> getMovieByScenaristId(@Param("scenaristId") Integer scenaristId);

    @Select("SELECT sum(m.boxoffice) from movie_table m " +
            "WHERE m.release_time >= STR_TO_DATE(#{y1}, '%Y-%m-%d') and m.release_time < STR_TO_DATE(#{y2}, '%Y-%m-%d')")
            //"WHERE m.release_time >= STR_TO_DATE('2010-01-01', '%Y-%m-%d') and m.release_time <= STR_TO_DATE('2010-12-31', '%Y-%m-%d')")
    Double getBoxByYear(@Param("y1") String year1, @Param("y2") String year2);
    //Double getBoxByYear();

    @Select("SELECT YEAR(m.release_time) as year, sum(m.boxoffice) as boxoffice from movie_table m " +
            "GROUP BY YEAR(m.release_time) ORDER BY year")
    List<YearBoxOffice> getBoxAllYear();

}

/*
<select id="getMovieByActorId" resultMap="BaseResultMap" parameterType="Integer">
        select * from actor_participate ap, movie_table m where ap.actor_id = #{actorId} and ap.movie_id = m.id
    </select>

    <select id="getMovieByDirectorId" resultMap="BaseResultMap" parameterType="Integer">
        select * from d_s_participate dsp, movie_table m where dsp.person_occu = 1 and dsp.person_id = #{directorId} and dsp.movie_id = m.id
    </select>

    <select id="getMovieByScenaristId" resultMap="BaseResultMap" parameterType="Integer">
        select * from d_s_participate dsp, movie_table m where dsp.person_occu = 2 and dsp.person_id = #{scenaristId} and dsp.movie_id = m.id
    </select>
 */