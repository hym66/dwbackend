package cn.edu.tongji.dwbackend.service.impl;

import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.dto.BasicMovie;
import cn.edu.tongji.dwbackend.dto.MovieProduct;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Movie;
import cn.edu.tongji.dwbackend.entity.Product;
import cn.edu.tongji.dwbackend.mapper.MovieMapper;
import cn.edu.tongji.dwbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Movie> selectGreaterMovie(float minScore) {
        return movieMapper.selectGreaterMovie(minScore);
    }

    @Override
    public List<BasicMovie> selectPositiveMovie() {
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
        //构建url
        for(Product p : productList){
            if(p.getSource().equals("amazon")) {
                p.setUrl("https://www.amazon.com/dp/" + p.getProductId());
            }
            else if(p.getSource().equals("imdb")){
                p.setUrl("https://www.imdb.com/title/" + p.getProductId());
            }
        }
        return productList;
    }

    @Override
    public List<MovieProduct> selectMovieProduct(String movieTitle) {
        List<Movie> movieList = movieMapper.selectNameMatchMovie(movieTitle);
        if(movieList == null || movieList.size() == 0){
            return null;
        }

        List<MovieProduct> movieProductList = new ArrayList<>();
        for(Movie m : movieList){
            List<Product> productList = movieMapper.selectSource(m.getMovieId());
            MovieProduct movieProduct = new MovieProduct(m.getMovieId(),m.getMovieTitle(),productList);
            movieProductList.add(movieProduct);
        }
        return movieProductList;
    }
}
