package com.fuyi.ex;

/**
 * 错误码定义
 *
 * @author xurenjie
 * @date on 2018-04-17 16:38
 */
public enum Errors {
    PARAM_ERROR(10001, "参数错误:%s"),

    //-----账户错误-----
    USERNAME_EXIST(20001, "用户名已使用"),
    MOBILE_EXIST(20002, "手机号已使用"),
    EMAIL_EXIST(20003, "邮箱已使用"),
    NEWPASSWORD_NOT_EQUAL(20004, "新密码输入不一致"),
    USER_NOT_EXIST(20005, "用户【%s】不存在"),

    //--oauth验证错误---
    OAUTH2_TOKEN_ACQUIRE_ERROR(30001, "验证失败: %s"),
    OAUTH2_TOKEN_VERIFY_ERROR(30002, "验证失败: %s"),
    OAUTH2_INVALID_SCOPE_ERROR(30003, "无效的scope"),
    OAUTH2_UNSUPPORTED_GRANT_TYPE_ERROR(30004, "不支持的grant type"),
    CLIENT_AUTHENCATION_ERROR(30005, "客户端认证错误"),

    //验证码错误
    SMS_EXCEED_LIMIT(40001, "短信验证码发送超过限制"),
    SMS_SEND_FAIL(40002, "短信发送失败:%s"),

    INTERNAL_ERROR(90000, "系统错误, 请稍后重试");

    private Integer code;
    private String msg;

    Errors(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
