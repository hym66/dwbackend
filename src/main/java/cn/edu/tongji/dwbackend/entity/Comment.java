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
 * @Author HUAWEI
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    Long commentId;
    int oneStarNum;
    int twoStarNum;
    int threeStarNum;
    int fourStarNum;
    int fiveStarNum;
    int commentNum;
}
