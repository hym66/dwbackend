package cn.edu.tongji.dwbackend.entity;

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
@TableName("genre_movie")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreMovie {
    @JsonSerialize(using= ToStringSerializer.class)
    Long movieId;
    String genreTitle;
}
