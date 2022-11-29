package cn.edu.tongji.dwbackend.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieNode {
    @Id
    @GeneratedValue
    @JsonSerialize(using= ToStringSerializer.class)
    private Long movieId;
}
