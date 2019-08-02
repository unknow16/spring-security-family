package com.fuyi.service;


import com.fuyi.entity.UserDetailsImpl;

import java.util.List;

/**
 * 用户相关服务
 */
public interface UserService {
    Long createUser(UserDetailsImpl user);

    void resetPassword(Long userId, String newPassword);

    void updatePassword(Long userId, String newPassword, String newPasswordRepeated);

    UserDetailsImpl queryUserById(Long userId);

    List<UserDetailsImpl> searchUsers(String mobile, String username, String email);
}
