package com.fuyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyi.common.ResultCode;
import com.fuyi.entity.UserDetailsImpl;
import com.fuyi.ex.BizException;
import com.fuyi.mapper.UserMapper;
import com.fuyi.service.UserService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Long createUser(UserDetailsImpl user) {

        //检查用户名，手机号或者邮箱是否已经被使用过
        QueryWrapper<UserDetailsImpl> userDetailsQueryWrapper = Condition.create();
        if (StringUtils.isNotEmpty(user.getMobile())) {
            userDetailsQueryWrapper = userDetailsQueryWrapper.eq("mobile", user.getMobile());
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            userDetailsQueryWrapper = userDetailsQueryWrapper.or().eq("email", user.getEmail());
        }
        if (StringUtils.isNotEmpty(user.getUsername())) {
            userDetailsQueryWrapper = userDetailsQueryWrapper.or().eq("username", user.getUsername());
        }
        List<UserDetailsImpl> existingUserDetails = userMapper.selectList(userDetailsQueryWrapper);
        if (existingUserDetails.size() > 0) {
            for (UserDetailsImpl userDetails : existingUserDetails) {
                if (StringUtils.isNotEmpty(user.getUsername())
                        && StringUtils.equals(userDetails.getUsername(), user.getUsername())) {
                    throw new BizException(ResultCode.USERNAME_EXIST);
                }
                if (StringUtils.isNotEmpty(user.getEmail())
                        && StringUtils.equals(userDetails.getEmail(), user.getEmail())) {
                    throw new BizException(ResultCode.EMAIL_EXIST);
                }
                if (StringUtils.isNotEmpty(user.getMobile())
                        && StringUtils.equals(userDetails.getMobile(), user.getMobile())) {
                    throw new BizException(ResultCode.MOBILE_EXIST);
                }
            }
        }

        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userMapper.insert(user);

        return user.getId();
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        Preconditions.checkNotNull(userId);
        Preconditions.checkArgument(StringUtils.isNotEmpty(newPassword), "新密码不能为空");

        UserDetailsImpl existingUserDetails = userMapper.selectById(userId);
        if (existingUserDetails == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        newPassword = passwordEncoder.encode(newPassword);
        UserDetailsImpl userDetails = UserDetailsImpl.builder().id(userId).password(newPassword).build();
        userMapper.updateById(userDetails);
    }

    @Override
    public void updatePassword(Long userId, String newPassword, String newPasswordRepeated) {
        Preconditions.checkNotNull(userId);
        Preconditions.checkArgument(StringUtils.isNotEmpty(newPassword) && StringUtils.isNotEmpty(newPasswordRepeated), "新密码不能为空");

        if (!StringUtils.equals(newPassword, newPasswordRepeated)) {
            throw new BizException(ResultCode.NEWPASSWORD_NOT_EQUAL);
        }
        UserDetailsImpl existingUserDetails = userMapper.selectById(userId);
        if (existingUserDetails == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        newPassword = passwordEncoder.encode(newPassword);
        UserDetailsImpl userDetails = UserDetailsImpl.builder().id(userId).password(newPassword).build();
        userMapper.updateById(userDetails);
    }

    @Override
    public UserDetailsImpl queryUserById(Long userId) {
        Preconditions.checkNotNull(userId);

        UserDetailsImpl userDetails = userMapper.selectById(userId);
        if (userDetails == null) {
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        return userDetails;
    }

    @Override
    public List<UserDetailsImpl> searchUsers(String mobile, String username, String email) {
        QueryWrapper<UserDetailsImpl> userDetailsQueryWrapper = Condition.create();
        if (StringUtils.isNotEmpty(mobile)) {
            userDetailsQueryWrapper.eq("mobile", mobile);
        }
        if (StringUtils.isNotEmpty(username)) {
            userDetailsQueryWrapper.or().eq("username", username);
        }
        if (StringUtils.isNotEmpty(email)) {
            userDetailsQueryWrapper.or().eq("email", email);
        }
        return userMapper.selectList(userDetailsQueryWrapper);
    }
}
