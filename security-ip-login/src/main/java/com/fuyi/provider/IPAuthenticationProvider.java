package com.fuyi.provider;

import com.fuyi.domin.IPAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
public class IPAuthenticationProvider implements AuthenticationProvider {

    static final Map<String, SimpleGrantedAuthority> ipAuthorityMap = new ConcurrentHashMap<>();
    static {
        ipAuthorityMap.put("192.168.0.128", new SimpleGrantedAuthority("ADMIN"));
        ipAuthorityMap.put("0:0:0:0:0:0:0:1", new SimpleGrantedAuthority("ADMIN"));
        ipAuthorityMap.put("192.168.0.112", new SimpleGrantedAuthority("USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof IPAuthenticationToken) {
            IPAuthenticationToken ipAuthenticationToken = (IPAuthenticationToken) authentication;
            String ip = ipAuthenticationToken.getIp();
            SimpleGrantedAuthority simpleGrantedAuthority = ipAuthorityMap.get(ip);
            if (simpleGrantedAuthority == null) {
                return null;
            } else {
                return new IPAuthenticationToken(ip, Arrays.asList(simpleGrantedAuthority));
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (IPAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
