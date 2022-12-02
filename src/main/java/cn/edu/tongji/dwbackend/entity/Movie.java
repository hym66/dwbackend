package cn.edu.tongji.dwbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@TableName("movie")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long movieId;
    private String movieTitle;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long timeId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long commentId;
    private float score;
    private int starNum;
    private int actorNum;
    private int directorNum;
    private int commentNum;
    private int productNum;
}
