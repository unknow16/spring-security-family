package com.fuyi.jwt.auth;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class JwtAuthenticationResponse implements Serializable {

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
