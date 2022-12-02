package cn.edu.tongji.dwbackend.controller;

import cn.edu.tongji.dwbackend.common.Result;
import cn.edu.tongji.dwbackend.dto.ActorActor;
import cn.edu.tongji.dwbackend.dto.ActorDirector;
import cn.edu.tongji.dwbackend.entity.Actor;
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
            int yearReleaseNum = movieService.selectYearReleaseNum(year);
            return Result.success(yearReleaseNum);
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
            int monthReleaseNum = movieService.selectMonthReleaseNum(year,month);
            return Result.success(monthReleaseNum);
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
            int seasonReleaseNum = movieService.selectSeasonReleaseNum(year,season);
            return Result.success(seasonReleaseNum);
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
            int productNum = movieService.selectProductNumByMovieId(movieId);
            return Result.success(productNum);
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
            int movieNum = movieService.selectDirectorMovieNum(directorName);
            return Result.success(movieNum);
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
            Actor actor = movieService.selectActorMovieNum(actorName);
            return Result.success(actor);
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
            int movieNum = movieService.selectGenreMovieNum(genreTitle);
            return Result.success(movieNum);
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
            List<String> movieList = movieService.selectGreaterMovie(minScore);
            return Result.success(movieList);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("查找有正面评价的所有电影名")
    @GetMapping("getPositiveMovie")
    public Result<List<String>> getPositiveMovie(){
        try{
            List<String> movieList = movieService.selectPositiveMovie();
            return Result.success(movieList);
        }
        catch(Exception e){
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("获取经常合作的导演和演员组合")
    @GetMapping("getOftenActorDirector")
    public Result<List<ActorDirector>> getOftenActorDirector(){
        try{
            List<ActorDirector> movieList = movieService.selectOftenActorDirector();
            return Result.success(movieList);
        }
        catch (Exception e){
            return Result.fail(500,e.getMessage());
        }

    }

    @ApiOperation("获取经常合作的导演和演员组合")
    @GetMapping("getOftenActorActor")
    public Result<List<ActorActor>> getOftenActorActor(){
        try{
            List<ActorActor> movieList = movieService.selectOftenActorActor();
            return Result.success(movieList);
        }
        catch (Exception e){
            return Result.fail(500,e.getMessage());
        }
    }
}
