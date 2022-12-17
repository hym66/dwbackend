package cn.edu.tongji.dwbackend.dto;

import cn.edu.tongji.dwbackend.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieProduct {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long movieId;
    private String movieTitle;
    private float score;
    private List<String> actor;
    private List<String> director;
    private String time;
    private int commentNum;
    private List<Product> productList;
}
