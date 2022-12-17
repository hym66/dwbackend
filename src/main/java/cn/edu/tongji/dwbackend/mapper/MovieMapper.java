package cn.edu.tongji.dwbackend.mapper;

import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.dto.BasicMovie;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Movie;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.entity.Product;
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
    Integer selectYearReleaseNum(@Param("year") short year);

    //XX年XX月有多少电影
    @Select("SELECT release_num FROM time_month WHERE year=${year} AND month=${month}")
    Integer selectMonthReleaseNum(@Param("year") short year, @Param("month") byte month);

    //XX年XX季度有多少电影
    @Select("SELECT release_num FROM time_season WHERE year=${year} AND season=${season}")
    Integer selectSeasonReleaseNum(@Param("year") short year, @Param("season") byte season);

    //周二新增多少电影
    //todo

    //2.电影名称
    //XX电影共有多少版本
    @Select("SELECT product_num FROM movie WHERE movie_id=${movieId}")
    Integer selectProductNumByMovieId(@Param("movieId") Long movieId);

    //3.导演
    //XX导演共有多少电影
    @Select("SELECT direct_movie_num FROM director WHERE director_name='${directorName}'")
    Integer selectDirectorMovieNum(@Param("directorName") String directorName);

    //4.演员
    //XX演员主演多少电影，XX演员参演多少电影
    @Select("SELECT actor_name, star_movie_num, act_movie_num FROM actor WHERE actor_name='${actorName}'")
    Actor selectActorMovieNum(@Param("actorName") String actorName);

    //6.电影类别
    //XX类型电影共有多少
    @Select("SELECT movie_num FROM genre WHERE genre_title='${genreTitle}'")
    Integer selectGenreMovieNum(@Param("genreTitle") String genreTitle);

    //7.用户评价
    //用户评分X分以上的电影有哪些
    @Select("SELECT movie_id, movie_title, score FROM movie WHERE score>=${minScore}")
    List<Movie> selectGreaterMovie(@Param("minScore") float minScore);

    //用户评价中有正面评价的电影
    @Select("SELECT movie_id, movie_title \n" +
            "FROM movie JOIN comment USING(comment_id)\n" +
            "WHERE three_star_num>0 OR four_star_num>0 OR five_star_num>0")
    List<BasicMovie> selectPositiveMovie();

    //5 8.演员和导演的关系
    //经常一起合作的演员和导演有哪些
    @Select("SELECT actor_name,director_name,COUNT(DISTINCT movie_id) AS cooperate_num " +
            "FROM actor_movie JOIN director_movie USING(movie_id) " +
            "GROUP BY actor_name,director_name " +
            "HAVING COUNT(DISTINCT movie_id)>20")
    List<ActorDirector> selectOftenActorDirector();

    //经常一起合作的演员
    @Select("SELECT actor_movie_1.actor_name,actor_movie_2.actor_name,COUNT(DISTINCT movie_id) AS cooperate_num " +
            "FROM actor_movie AS actor_movie_1 JOIN actor_movie AS actor_movie_2 USING(movie_id) " +
            "WHERE actor_movie_1.actor_name <> actor_movie_2.actor_name " +
            "GROUP BY actor_movie_1.actor_name,actor_movie_2.actor_name " +
            "HAVING COUNT(DISTINCT movie_id)>20")
    List<ActorActor> selectOftenActorActor();

    //溯源查询：根据movieId查询，一个电影的源product都有哪些
    @Select("SELECT product_id, source " +
            "FROM product " +
            "WHERE movie_id=${movieId}")
    List<Product> selectSource(@Param("movieId") Long movieId);


    @Select("SELECT movie_id FROM genre_movie WHERE genre_title='${genreTitle}'")
    List<Long> selectMovieByGenre(@Param("genreTitle")String genreTitle);

    @Select("SELECT movie_id FROM director_movie WHERE director_name='${director_name}'")
    List<Long> selectMovieByDirector(@Param("director_name")String director_name);

    @Select("SELECT movie_id FROM actor_movie WHERE actor_name='${name}' AND is_star=1")
    List<Long> selectMovieByStar(@Param("name")String name);

    @Select("SELECT movie_id FROM actor_movie WHERE actor_name='${name}'")
    List<Long> selectMovieByActor(@Param("name")String name);

    //根据movieTitle，能够查询到多少部movie
    @Select("SELECT *\n" +
            "FROM movie\n" +
            "WHERE movie_title like('%${movieTitle}%')")
    List<Movie> selectNameMatchMovie(@Param("movieTitle") String movieTitle);

}
