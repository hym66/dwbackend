package cn.edu.tongji.dwbackend.service;

import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    int selectYearReleaseNum(@Param("year") short year);
    int selectMonthReleaseNum(@Param("year") short year, @Param("month") byte month);
    int selectSeasonReleaseNum(@Param("year") short year, @Param("month") byte season);
    int selectProductNumByMovieId(@Param("movieId") Long movieId);
    int selectDirectorMovieNum(@Param("directorName") String directorName);
    Actor selectActorMovieNum(@Param("actorName") String actorName);
    int selectGenreMovieNum(@Param("genreTitle") String genreTitle);
    List<String> selectGreaterMovie(@Param("minScore") float minScore);
    List<String> selectPositiveMovie();
    List<ActorDirector> selectOftenActorDirector();
    List<ActorActor> selectOftenActorActor();
}
