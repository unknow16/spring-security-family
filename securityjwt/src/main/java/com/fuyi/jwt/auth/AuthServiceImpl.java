package com.fuyi.jwt.auth;

import com.fuyi.jwt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthServiceImpl(UserDetailsService userDetailsService,
                           AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public User register(User userToAdd) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        //securityConfig中配置的auth允许，所以不会被UsernamePasswordAuthenticationFilter拦截，在此颁发token前，自己执行认证
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // 其实现类ProviderManager中List<AuthenticationProvider>只包含一个DaoAuthenticationProvider，
        // 该provider调用UserDetailsService的loadUserByUsername()比对认证
        // 返回填充角色权限的Authentication(其是UsernamePasswordAuthenticationToken类型)
        final Authentication authenticate = authenticationManager.authenticate(token);

        // 设置进SecurityContextHolder中的安全上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //根据查到的信息生成token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return jwtToken;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
