package com.xzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.entity.Director;
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
public interface DirectorMapper extends BaseMapper<Director> {

    @Select("select d.* from d_s_participate dsp, director_table d where dsp.movie_id = #{movieId} and dsp.person_occu = 1 and dsp.person_id = d.id")
    List<Director> getDirectorByMovieId(@Param("movieId") Integer movieId);

}
