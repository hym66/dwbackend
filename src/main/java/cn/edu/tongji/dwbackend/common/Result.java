package cn.edu.tongji.dwbackend.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private boolean success;
    private int code;
    private String msg;
    private long time;
    private T data;

    public static <T> Result<T> success(T data, long time){
        return new Result<>(true,200,"success",time,data);
    }
    public static <T> Result<T> fail(int code, String msg){
        return new Result<>(true,code,msg,0,null);
    }

}
