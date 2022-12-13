package cn.edu.tongji.dwbackend.controller;

import cn.edu.tongji.dwbackend.common.Result;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.entity.Actor;
import cn.edu.tongji.dwbackend.entity.Product;
import cn.edu.tongji.dwbackend.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */

@Api(tags = {"MySQL"})
@RestController
@RequestMapping("mysql")
public class mysqlController {
    @Autowired
    MovieService movieService;

    @ApiOperation("获取指定年的电影数量")
    @GetMapping("getYearReleaseNum")
    public Result<Integer> getYearReleaseNum(@ApiParam(name="year", value="要查找的年份", required = true)
                                                @RequestParam("year") short year){
        try {
            long start=System.currentTimeMillis();
            Integer yearReleaseNum = movieService.selectYearReleaseNum(year);
            long end=System.currentTimeMillis();
            return Result.success(yearReleaseNum,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取指定月份的电影数量")
    @GetMapping("getMonthReleaseNum")
    public Result<Integer> getMonthReleaseNum(@ApiParam(name="year", value="要查找的年份", required = true)
                                                  @RequestParam("year") short year,
                                              @ApiParam(name="month", value="要查找的月份", required = true)
                                                  @RequestParam("month") byte month){
        try{
            long start=System.currentTimeMillis();
            Integer monthReleaseNum = movieService.selectMonthReleaseNum(year,month);
            long end=System.currentTimeMillis();
            return Result.success(monthReleaseNum,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取指定季度的电影数量")
    @GetMapping("getSeasonReleaseNum")
    public Result<Integer> getSeasonReleaseNum(@ApiParam(name="year", value="要查找的年份", required = true)
                                                   @RequestParam("year") short year,
                                               @ApiParam(name="season", value="要查找的季度(1,2,3,4)", required = true)
                                                   @RequestParam("season") byte season){
        try{
            long start=System.currentTimeMillis();
            Integer seasonReleaseNum = movieService.selectSeasonReleaseNum(year,season);
            long end=System.currentTimeMillis();
            return Result.success(seasonReleaseNum,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取指定电影的版本数量")
    @GetMapping("getProductNumByMovieId")
    public Result<Integer> getProductNumByMovieId(@ApiParam(name="movieId", value="要查找的movieId", required = true)
                                                      @RequestParam("movieId") Long movieId){
        try{
            long start=System.currentTimeMillis();
            Integer productNum = movieService.selectProductNumByMovieId(movieId);
            long end=System.currentTimeMillis();
            return Result.success(productNum,end-start);
        }
        catch (Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取某个导演执导过的电影数量")
    @GetMapping("getDirectorMovieNum")
    public Result<Integer> getDirectorMovieNum(@ApiParam(name="directorName", value="要查找的导演名字", required = true)
                                                   @RequestParam("directorName") String directorName){
        try{
            long start=System.currentTimeMillis();
            Integer movieNum = movieService.selectDirectorMovieNum(directorName);
            long end=System.currentTimeMillis();
            return Result.success(movieNum,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取某个演员主演，参演过的电影数量")
    @GetMapping("getActorMovieNum")
    public Result<Actor> getActorMovieNum(@ApiParam(name="actorName", value="要查找的演员名字", required = true)
                                               @RequestParam("actorName") String actorName){
        try{
            long start=System.currentTimeMillis();
            Actor actor = movieService.selectActorMovieNum(actorName);
            long end=System.currentTimeMillis();
            return Result.success(actor,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取某个类型的电影数量")
    @GetMapping("getGenreMovieNum")
    public Result<Integer> getGenreMovieNum(@ApiParam(name="genreTitle", value="要查找的电影类型名称", required = true)
                                                @RequestParam("genreTitle") String genreTitle){
        try{
            long start=System.currentTimeMillis();
            Integer movieNum = movieService.selectGenreMovieNum(genreTitle);
            long end=System.currentTimeMillis();
            return Result.success(movieNum,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取xx评分以上的所有电影名")
    @GetMapping("getGreaterMovie")
    public Result<List<String>> getGreaterMovie(@ApiParam(name="minScore", value="评分下限", required = true)
                                                    @RequestParam("minScore") float minScore){
        try{
            long start=System.currentTimeMillis();
            List<String> movieList = movieService.selectGreaterMovie(minScore);
            long end=System.currentTimeMillis();
            return Result.success(movieList,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("查找有正面评价的所有电影名")
    @GetMapping("getPositiveMovie")
    public Result<List<String>> getPositiveMovie(){
        try{
            long start=System.currentTimeMillis();
            List<String> movieList = movieService.selectPositiveMovie();
            long end=System.currentTimeMillis();
            return Result.success(movieList,end-start);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取经常合作的导演和演员组合")
    @GetMapping("getOftenActorDirector")
    public Result<List<ActorDirector>> getOftenActorDirector(){
        try{
            long start=System.currentTimeMillis();
            List<ActorDirector> movieList = movieService.selectOftenActorDirector();
            long end=System.currentTimeMillis();
            return Result.success(movieList,end-start);
        }
        catch (Exception e){
            return Result.fail(500,e.getMessage());
        }

    }

    @ApiOperation("获取经常合作的导演和演员组合")
    @GetMapping("getOftenActorActor")
    public Result<List<ActorActor>> getOftenActorActor(){
        try{
            long start=System.currentTimeMillis();
            List<ActorActor> movieList = movieService.selectOftenActorActor();
            long end=System.currentTimeMillis();
            return Result.success(movieList,end-start);
        }
        catch (Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("溯源查询：查询与一个电影相关的所有product")
    @GetMapping("getSource")
    public Result<List<Product>> getSource(@ApiParam(name="movieId", value="要查询的movieId", required = true)
                                               @RequestParam("movieId") Long movieId){
        try{
            long start=System.currentTimeMillis();
            List<Product> productList = movieService.selectSource(movieId);
            long end=System.currentTimeMillis();
            return Result.success(productList, end-start);
        }
        catch (Exception e){
            return Result.fail(500,e.getMessage());
        }
    }
}
