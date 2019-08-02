package com.fuyi.common;

import lombok.Data;
import lombok.ToString;

/**
 * Api统一的返回结果类
 */
@Data
@ToString
public class ApiResult {
    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;


    public ApiResult() {

    }

    public ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * 生成一个ApiResult对象, 并返回
     *
     * @param resultCode
     * @return
     */
    public static ApiResult of(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }

}