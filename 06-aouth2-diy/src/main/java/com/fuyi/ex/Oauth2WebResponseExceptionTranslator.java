package com.fuyi.ex;

import com.fuyi.common.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;


/**
 * @Date 2019/8/6 16:15
 * @Created by minfy
 */
public class Oauth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {


    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        if (e instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity.ok(ApiResult.of(oAuth2Exception.getHttpErrorCode(), oAuth2Exception.getMessage()));
        } else if(e instanceof AuthenticationException){
            AuthenticationException authenticationException = (AuthenticationException) e;
            return ResponseEntity.ok(ApiResult.of(HttpStatus.UNAUTHORIZED.value(), authenticationException.getMessage()));
        }
        throw e;
    }

}
