package com.fuyi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VerifyCode {
    private String code;

    private LocalDateTime expiredTime;

    public VerifyCode(String code, int expireInSecs) {
        this(code, LocalDateTime.now().plusSeconds(expireInSecs));
    }

    public VerifyCode(String code, LocalDateTime expiredTime) {
        this.code = code;
        this.expiredTime = expiredTime;
    }
}