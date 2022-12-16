package cn.edu.tongji.dwbackend.service;

import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.dto.BasicMovie;
import cn.edu.tongji.dwbackend.dto.MovieQuery;
import cn.edu.tongji.dwbackend.dto.MovieProduct;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.entity.Movie;
import cn.edu.tongji.dwbackend.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    Integer selectYearReleaseNum(@Param("year") short year);
    Integer selectMonthReleaseNum(@Param("year") short year, @Param("month") byte month);
    Integer selectSeasonReleaseNum(@Param("year") short year, @Param("month") byte season);
    Integer selectProductNumByMovieId(@Param("movieId") Long movieId);
    Integer selectDirectorMovieNum(@Param("directorName") String directorName);
    Actor selectActorMovieNum(@Param("actorName") String actorName);
    Integer selectGenreMovieNum(@Param("genreTitle") String genreTitle);
    List<Movie> selectGreaterMovie(@Param("minScore") float minScore);
    List<BasicMovie> selectPositiveMovie();
    List<ActorDirector> selectOftenActorDirector();
    List<ActorActor> selectOftenActorActor();
    List<Product> selectSource(Long movieId);
    List<BasicMovie> selectMovieByMovieQuery(MovieQuery movieQuery);
    List<MovieProduct> selectMovieProduct(String movieTitle);
}
