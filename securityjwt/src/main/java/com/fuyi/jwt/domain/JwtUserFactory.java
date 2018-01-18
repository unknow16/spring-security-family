package com.fuyi.jwt.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public final class JwtUserFactory {
    private JwtUserFactory() {}

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), user.getDesc(), mapToGrantedAuthorities(user.getRoles()));
    }

    private static Collection<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
