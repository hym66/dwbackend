package cn.edu.tongji.dwbackend.mapper;

import cn.edu.tongji.dwbackend.entity.Time;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TimeMapper extends BaseMapper<Time> {
    @Select("SELECT time_id FROM time WHERE ((year > ${year1}" +
            " OR (year = ${year1} AND month > ${month1}) OR (year = ${year1} " +
            "AND month = ${month1} AND weekday >= ${day1})) " +
            "AND (year < ${year2} OR (year = ${year2} AND month < ${month2}) " +
            "OR (year = ${year2} AND month = ${month2} AND weekday <= ${day2})))")
    List<Long> selectTimeidBetweenRange(@Param("year1")short year1,
                                        @Param("month1")byte month1,
                                        @Param("day1")byte day1,
                                        @Param("year2") short year2,
                                        @Param("month2") byte month2,
                                        @Param("day2") byte day2);
    @Select("SELECT * FROM time WHERE time_id=${timeId}")
    Time selectTimeById(@Param("timeId")Long timeId);
}
