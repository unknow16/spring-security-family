package com.fuyi.jwt.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class JwtUser implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final Integer status;
    private final String desc;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String id, String username, String password, Integer status, String desc, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.desc = desc;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status.intValue() == 1 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status.intValue() == 1 ? true : false;
    }

    @Override
    public boolean isEnabled() {
        return this.status.intValue() == 1 ? true : false;
    }
}
