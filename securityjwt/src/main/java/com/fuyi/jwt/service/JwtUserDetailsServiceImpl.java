package com.fuyi.jwt.service;

import com.fuyi.jwt.domain.JwtUserFactory;
import com.fuyi.jwt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //从数据库查出user
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        User user = new User("123", "fuyi", "123", 1, "haha", roles);

        if (user != null) {
            return JwtUserFactory.create(user);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
