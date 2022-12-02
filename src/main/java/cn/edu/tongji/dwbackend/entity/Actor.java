package cn.edu.tongji.dwbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("actor")
public class Actor {
    String actorName;
    int starMovieNum;
    int actMovieNum;
}
