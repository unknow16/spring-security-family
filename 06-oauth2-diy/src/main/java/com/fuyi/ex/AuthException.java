package com.fuyi.ex;

import com.fuyi.common.ResultCode;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @Date 2019/8/2 17:18
 * @Created by minfy
 */
@Data
public class AuthException extends AuthenticationException {

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


    public AuthException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.resultCode = resultCode;
    }
}
