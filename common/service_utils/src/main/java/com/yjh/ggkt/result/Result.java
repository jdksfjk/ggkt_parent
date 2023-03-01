package com.yjh.ggkt.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result(){}

    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<T>();
        if (body != null) {
            result.setData(body);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(T data){
        return build(data,20000,"成功");
    }

    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        return build(data, 20001,"失败");
    }

    public static<T> Result<T> fail(){
        return Result.fail(null);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }




}
