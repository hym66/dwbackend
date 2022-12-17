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
public interface DirectorMovieMapper {
    @Select("SELECT director_name FROM director_movie WHERE movie_id = ${movieId}")
    List<String> selectTDirectorByMovieId(@Param("movieId")Long movieId);
}
