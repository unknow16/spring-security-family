package com.fuyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyi.common.ResultCode;
import com.fuyi.entity.UserDetailsImpl;
import com.fuyi.ex.AuthException;
import com.fuyi.mapper.UserMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Date 2019/8/2 9:49
 * @Created by minfy
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
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
            throw new AuthException(ResultCode.USER_NOT_EXIST);
        }
        return userDetails;
    }

}
