package cn.edu.tongji.dwbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/17
 * @JDKVersion 17.0.4
 */
@Mapper
public interface ActorMovieMapper {
    @Select("SELECT actor_name FROM actor_movie WHERE movie_id = ${movieId}")
    List<String> selectTActorByMovieId(@Param("movieId")Long movieId);
}
