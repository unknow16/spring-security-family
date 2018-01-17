package com.fuyi.domin;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
public class IPAuthenticationToken extends AbstractAuthenticationToken {

    private String ip;

    public IPAuthenticationToken(String ip) {
        super(null);
        this.ip = ip;
        super.setAuthenticated(false);
    }

    public IPAuthenticationToken(String ip, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.ip = ip;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
