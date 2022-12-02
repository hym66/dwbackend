package cn.edu.tongji.dwbackend.mapper;

import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Movie;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper extends BaseMapper<Movie> {

    //1.时间
    //XX年有多少电影
    @Select("SELECT release_num FROM time_year WHERE year=${year}")
    int selectYearReleaseNum(@Param("year") short year);

    //XX年XX月有多少电影
    @Select("SELECT release_num FROM time_month WHERE year=${year} AND month=${month}")
    int selectMonthReleaseNum(@Param("year") short year, @Param("month") byte month);

    //XX年XX季度有多少电影
    @Select("SELECT release_num FROM time_month WHERE year=${year} AND season=${season}")
    int selectSeasonReleaseNum(@Param("year") short year, @Param("month") byte season);

    //周二新增多少电影
    //todo

    //2.电影名称
    //XX电影共有多少版本
    @Select("SELECT product_num FROM movie WHERE movie_id=${movieId}")
    int selectProductNumByMovieId(@Param("movieId") Long movieId);

    //3.导演
    //XX导演共有多少电影
    @Select("SELECT direct_movie_num FROM director WHERE director_name=${directorName}")
    int selectDirectorMovieNum(@Param("directorName") String directorName);

    //4.演员
    //XX演员主演多少电影，XX演员参演多少电影
    @Select("SELECT star_movie_num, act_movie_num FROM FROM actor WHERE actor_name=${actorName}")
    Actor selectActorMovieNum(@Param("actorName") String actorName);

    //6.电影类别
    //XX类型电影共有多少
    @Select("SELECT movie_num FROM genre WHERE genre_title=${genreTitle}")
    int selectGenreMovieNum(@Param("genreTitle") String genreTitle);

    //7.用户评价
    //用户评分X分以上的电影有哪些
    @Select("SELECT movie_title FROM movie WHERE score>${minScore}")
    List<String> selectGreaterMovie(@Param("minScore") float minScore);

    //用户评价中有正面评价的电影
    @Select("SELECT movie_title FROM movie WHERE three_star_num>0 OR four_star_num>0 OR five_star_num>0")
    List<String> selectPositiveMovie();

    //5 8.演员和导演的关系
    //经常一起合作的演员和导演有哪些
    @Select("SELECT actor_name,director_name,COUNT(DISTINCT movie_id) AS cooperate_num " +
            "FROM actor_movie JOIN director_movie USING(movie_id) " +
            "GROUP BY actor_name,director_name " +
            "HAVING COUNT(DISTINCT movie_id)>=3")
    List<ActorDirector> selectOftenActorDirector();

    //经常一起合作的演员
    @Select("SELECT actor_movie_1.actor_name,actor_movie_2.actor_name,COUNT(DISTINCT movie_id) AS cooperate_num " +
            "FROM actor_movie AS actor_movie_1 JOIN actor_movie AS actor_movie_2 USING(movie_id) " +
            "WHERE actor_movie_1.actor_name <> actor_movie_2.actor_name " +
            "GROUP BY actor_movie_1.actor_name,actor_movie_2.actor_name " +
            "HAVING COUNT(DISTINCT movie_id)>=3")
    List<ActorActor> selectOftenActorActor();


}