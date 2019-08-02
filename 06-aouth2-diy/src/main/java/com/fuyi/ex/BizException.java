package com.fuyi.ex;

import com.fuyi.common.ResultCode;

/**
 * @Date 2019/8/2 17:39
 * @Created by minfy
 */
public class BizException extends RuntimeException {

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;

    /**
     * 结果码枚举
     */
    private ResultCode resultCode;


    public BizException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.resultCode = resultCode;
    }
}
