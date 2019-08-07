package com.fuyi.controller;

import com.fuyi.common.ApiResult;
import com.fuyi.entity.UserDetailsImpl;
import com.fuyi.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户相关接口
 *
 * @author xurenjie
 * @date on 2018-11-07 13:57
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ApiResult searchUsers(String mobile, String username, String email) {
        List<UserDetailsImpl> users = userService.searchUsers(mobile, username, email);
        return ApiResult.success(users);
    }

    @GetMapping
    public ApiResult queryUserById(@NotNull(message = "用户id不能为空") Long userId) {
        UserDetailsImpl userDetails = userService.queryUserById(userId);
        return ApiResult.success(userDetails);
    }

    @PostMapping
    public ApiResult createUser(@Validated UserDetailsImpl user) {
        Long userId = userService.createUser(user);
        return ApiResult.success(userId);
    }

    @PostMapping("/password/reset")
    public void resetPassword(@NotNull(message = "用户id不能为空") Long userId,
                              @NotEmpty(message = "新密码不能为空") String newPassword) {
        userService.resetPassword(userId, newPassword);
    }

    @PostMapping("/password/update")
    public void updatePassword(@NotNull(message = "用户id不能为空") Long userId,
                               @NotEmpty(message = "新密码不能为空") String newPassword,
                               @NotEmpty(message = "新密码不能为空") String newPasswordRepeated) {
        userService.updatePassword(userId, newPassword, newPasswordRepeated);
    }
}
