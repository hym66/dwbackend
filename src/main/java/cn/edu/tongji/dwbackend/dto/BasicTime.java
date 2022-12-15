package cn.edu.tongji.dwbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BasicTime {
    private short year;
    private byte month;
    private byte day;
}
