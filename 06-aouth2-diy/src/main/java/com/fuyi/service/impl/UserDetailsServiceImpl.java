package com.fuyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyi.entity.UserDetailsImpl;
import com.fuyi.ex.Errors;
import com.fuyi.mapper.UserMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Date 2019/8/2 9:49
 * @Created by minfy
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserDetailsImpl> queryWrapper = Condition.create();
        queryWrapper = queryWrapper.or()
                .eq("mobile", username)
                .or()
                .eq("username", username)
                .or()
                .eq("email", username);
        if (NumberUtils.isDigits(username)) {
            queryWrapper = queryWrapper.or()
                    .eq("id", username);

        }
        UserDetails userDetails = userMapper.selectOne(queryWrapper);
        if (userDetails == null) {
            throw new UsernameNotFoundException(String.format(Errors.USER_NOT_EXIST.getMsg(), username));
        }
        return userDetails;
    }

}
