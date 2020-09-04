package com.xzx.servie;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzx.dto.SimpleMovie;
import com.xzx.dto.YearBoxOffice;
import com.xzx.entity.Movie;
import com.xzx.vo.HintVo;
import com.xzx.vo.SearchVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzx
 * @since 2020-07-18
 */
public interface IMovieService extends IService<Movie> {

    List<SimpleMovie> getSimpleMovieByActorId(Integer actorId);

    List<SimpleMovie> getSimpleMovieByDirectorId(Integer directorId);

    List<SimpleMovie> getSimpleMovieByScenaristId(Integer scenaristId);

    List<Movie> getLikeMovieWithLimit(SearchVo searchVo);

    long getLikeMovieCount(SearchVo searchVo);

    List<Movie> getFirstLikeMovie(HintVo hintVo);

    List<SimpleMovie> getTopMovie(String column);

    Integer getMovieCountByType(String type);

    Double getMovieBoxByYear(Integer year);

    List<YearBoxOffice> getBoxAllYear();
}
