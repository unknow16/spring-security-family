package com.fuyi.filter;

import com.fuyi.domin.IPAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
public class IPAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public IPAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/ipVerify"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String ip = httpServletRequest.getRemoteHost();
        return getAuthenticationManager().authenticate(new IPAuthenticationToken(ip));
    }
}
