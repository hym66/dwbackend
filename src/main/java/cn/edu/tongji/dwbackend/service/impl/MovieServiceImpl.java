package cn.edu.tongji.dwbackend.service.impl;

import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Product;
import cn.edu.tongji.dwbackend.mapper.MovieMapper;
import cn.edu.tongji.dwbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author HUAWEI
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieMapper movieMapper;

    @Override
    public Integer selectYearReleaseNum(short year) {
        return movieMapper.selectYearReleaseNum(year);
    }

    @Override
    public Integer selectMonthReleaseNum(short year, byte month) {
        return movieMapper.selectMonthReleaseNum(year, month);
    }

    @Override
    public Integer selectSeasonReleaseNum(short year, byte season) {
        return movieMapper.selectSeasonReleaseNum(year, season);
    }

    @Override
    public Integer selectProductNumByMovieId(Long movieId) {
        return movieMapper.selectProductNumByMovieId(movieId);
    }

    @Override
    public Integer selectDirectorMovieNum(String directorName) {
        return movieMapper.selectDirectorMovieNum(directorName);
    }

    @Override
    public Actor selectActorMovieNum(String actorName) {
        return movieMapper.selectActorMovieNum(actorName);
    }

    @Override
    public Integer selectGenreMovieNum(String genreTitle) {
        return movieMapper.selectGenreMovieNum(genreTitle);
    }

    @Override
    public List<String> selectGreaterMovie(float minScore) {
        return movieMapper.selectGreaterMovie(minScore);
    }

    @Override
    public List<String> selectPositiveMovie() {
        return movieMapper.selectPositiveMovie();
    }

    @Override
    public List<ActorDirector> selectOftenActorDirector() {
        return movieMapper.selectOftenActorDirector();
    }

    @Override
    public List<ActorActor> selectOftenActorActor() {
        return movieMapper.selectOftenActorActor();
    }

    @Override
    public List<Product> selectSource(Long movieId) {
        List<Product> productList = movieMapper.selectSource(movieId);
        return productList;
    }
}
