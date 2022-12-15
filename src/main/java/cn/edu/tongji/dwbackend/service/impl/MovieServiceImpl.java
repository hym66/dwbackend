package cn.edu.tongji.dwbackend.service.impl;

<<<<<<< HEAD
import cn.edu.tongji.dwbackend.common.Result;
import cn.edu.tongji.dwbackend.dto.*;
=======
import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.dto.BasicMovie;
import cn.edu.tongji.dwbackend.dto.MovieProduct;
>>>>>>> origin/master
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Movie;
import cn.edu.tongji.dwbackend.entity.Product;
import cn.edu.tongji.dwbackend.entity.Time;
import cn.edu.tongji.dwbackend.mapper.MovieMapper;
import cn.edu.tongji.dwbackend.mapper.TimeMapper;
import cn.edu.tongji.dwbackend.service.MovieService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.*;
import java.util.function.Consumer;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> origin/master

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
<<<<<<< HEAD
    public List<BasicMovie> selectMovieByMovieQuery(MovieQuery movieQuery) {
        QueryWrapper<Movie> queryWrapper_movie=new QueryWrapper<>();
        if (movieQuery.getMovieTitle()!=null) {
            System.out.println("title");
            queryWrapper_movie.like("movie_title", movieQuery.getMovieTitle());
        }
        if (movieQuery.getGenreTitle()!=null){
            System.out.println("genre_title");
            List<Long> movie_idList=movieMapper.selectMovieByGenre(movieQuery.getGenreTitle());
            queryWrapper_movie.like("movie_id",movie_idList);
        }
        if ( movieQuery.getStartDate()!=null && movieQuery.getEndDate() != null){
            System.out.println("date");
            List<Long> time_idList=timeMapper.selectTimeidBetweenRange(movieQuery.getStartDate().getYear(),
                    movieQuery.getStartDate().getMonth(),
                    movieQuery.getStartDate().getDay(),
                    movieQuery.getEndDate().getYear(),
                    movieQuery.getEndDate().getMonth(),
                    movieQuery.getEndDate().getDay());
            queryWrapper_movie.in("time_id", time_idList);
        }

        if (movieQuery.getDirectorName() != null){
            for (String name:movieQuery.getDirectorName())
                queryWrapper_movie.in("movie_id",movieMapper.selectMovieByDirector(name));
        }
        if (movieQuery.getStarName()!=null){
            for (String name:movieQuery.getStarName())
                queryWrapper_movie.in("movie_id",movieMapper.selectMovieByStar(name));
        }
        if (movieQuery.getActorName()!=null){
            for (String name:movieQuery.getActorName())
                queryWrapper_movie.in("movie_id",movieMapper.selectMovieByActor(name));
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

    public List<Long>laterThanStart(BasicTime start, BasicTime end){
        QueryWrapper<Time> queryWrapper=new QueryWrapper<>();
        QueryWrapper<Time> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.lt("year",end.getYear()).or(wrapper -> wrapper
                .eq("year",end.getYear()).lt("month",end.getMonth())).or(wrapper -> wrapper
                .eq("year",end.getYear()).eq("month",end.getMonth()).le("weekday",end.getDay()));
        queryWrapper.gt("year",start.getYear()).or(wrapper -> wrapper
                .eq("year",start.getYear()).gt("month",start.getMonth())).or(wrapper -> wrapper
                .eq("year",start.getYear()).eq("month",start.getMonth()).ge("weekday",start.getDay()));
        timeMapper.selectList(queryWrapper);
        List<Time> timeList = timeMapper.selectList(queryWrapper);
        List<Long> time_idList = new ArrayList<>();
        for (Time time:timeList)
            time_idList.add(time.getTimeId());
        return time_idList;
=======
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
>>>>>>> origin/master
    }
}
