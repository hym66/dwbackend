package cn.edu.tongji.dwbackend.service.impl;

import cn.edu.tongji.dwbackend.dto.*;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.dto.BasicMovie;
import cn.edu.tongji.dwbackend.dto.MovieProduct;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Movie;
import cn.edu.tongji.dwbackend.entity.Product;
import cn.edu.tongji.dwbackend.mapper.MovieMapper;
import cn.edu.tongji.dwbackend.mapper.TimeMapper;
import cn.edu.tongji.dwbackend.service.MovieService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    TimeMapper timeMapper;

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
    public List<BasicMovie> selectMovieByMovieQuery(MovieQuery movieQuery) {
        QueryWrapper<Movie> queryWrapper_movie=new QueryWrapper<>();
        if (movieQuery.getMovieTitle()!=null) {
            System.out.println("title");
            queryWrapper_movie.like("movie_title", movieQuery.getMovieTitle());
        }
        if (movieQuery.getGenreTitle()!=null){
            System.out.println("genre_title");
            List<Long> movie_idList=movieMapper.selectMovieByGenre(movieQuery.getGenreTitle());
            if (movie_idList.size()>0)
                queryWrapper_movie.like("movie_id",movie_idList);
            else
                return new ArrayList<>();
        }
        if ( movieQuery.getStartTime()!=null && movieQuery.getEndTime() != null){
            System.out.println("date");


            String[] startTimeList = movieQuery.getStartTime().split("-");
            String[] endTimeList = movieQuery.getStartTime().split("-");

            BasicTime startTime = new BasicTime(Short.parseShort(startTimeList[0]),
                    Byte.parseByte(startTimeList[1]),
                    Byte.parseByte(startTimeList[2]));
            BasicTime endTime = new BasicTime(Short.parseShort(endTimeList[0]),
                    Byte.parseByte(endTimeList[1]),
                    Byte.parseByte(endTimeList[2]));

            List<Long> time_idList = timeMapper.selectTimeidBetweenRange(startTime.getYear(),
                    startTime.getMonth(),
                    startTime.getDay(),
                    endTime.getYear(),
                    endTime.getMonth(),
                    endTime.getDay());
            if (time_idList.size()>0)
                queryWrapper_movie.in("time_id", time_idList);
            else
                return new ArrayList<>();
        }

        if (movieQuery.getDirectorList() != null){
            for (String name:movieQuery.getDirectorList()) {
                List<Long> movie_idList = movieMapper.selectMovieByDirector(name);
                if (movie_idList.size()>0)
                    queryWrapper_movie.in("movie_id", movie_idList);
                else
                    return new ArrayList<>();
            }
        }
        if (movieQuery.getStarList()!=null){
            for (String name:movieQuery.getStarList()) {
                List<Long> movie_idList = movieMapper.selectMovieByStar(name);
                if (movie_idList.size()>0)
                    queryWrapper_movie.in("movie_id", movie_idList);
                else
                    return new ArrayList<>();
            }
        }
        if (movieQuery.getActorList()!=null){
            for (String name:movieQuery.getActorList()) {
                List<Long> movie_idList = movieMapper.selectMovieByActor(name);
                if (movie_idList.size()>0)
                    queryWrapper_movie.in("movie_id", movie_idList);
                else
                    return new ArrayList<>();
            }
        }
        if (movieQuery.getMaxScore()>1e-7){
            System.out.println("socre");
            queryWrapper_movie.between("score",movieQuery.getMinScore(),movieQuery.getMaxScore());
        }
//        movieMapper.selectList(queryWrapper_movie);
        List<BasicMovie> basicMovies=new ArrayList<>();
        for (Movie movie:movieMapper.selectList(queryWrapper_movie)){
            BasicMovie basicMovie=new BasicMovie();
            basicMovie.setMovieId(movie.getMovieId());
            basicMovie.setMovieTitle(movie.getMovieTitle());
            basicMovies.add(basicMovie);
        }
        return basicMovies;
    }


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

    @Override
    public List<Long> selectMovieByActor(String actor_name) {
        return movieMapper.selectMovieByActor(actor_name);
    }
}
