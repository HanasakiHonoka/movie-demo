package com.xzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.dto.PeopleWithBox;
import com.xzx.entity.Scenarist;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
@Repository
public interface ScenaristMapper extends BaseMapper<Scenarist> {

    @Select("select s.* from d_s_participate dsp, scenarist_table s where dsp.movie_id=#{movieId} and dsp.person_occu = 2 and s.id = dsp.person_id")
    List<Scenarist> getScenaristByMovieId(@Param("movieId") Integer movieId);

    @Select("select a.id, a.`name`, a.boxoffice, a.amount from scenarist_table a " +
            "where a.boxoffice is NOT NULL and a.amount is not null " +
            "ORDER BY boxoffice DESC limit 10")
    List<PeopleWithBox> getTopScenaristWithBox();
}
