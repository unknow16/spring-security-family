package com.fuyi.common;

import lombok.Data;
import lombok.ToString;

/**
 * Api统一的返回结果类
 */
@Data
@ToString
public class ApiResult<T> {
    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;

    /**
     * 结果数据
     */
    private T data;

    private ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ApiResult(Integer code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    /**
     * 生成一个ApiResult对象, 并返回
     */
    public static ApiResult success(Object data) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static ApiResult error(ResultCode resultCode) {
        return new ApiResult(resultCode.getCode(), resultCode.getMsg());
    }

    public static ApiResult of(Integer code, String msg) {
        return new ApiResult(code, msg);
    }

    public static ApiResult of(Integer code, String msg, String data) {
        return new ApiResult(code, msg, data);
    }
}