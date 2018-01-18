package com.fuyi.jwt.controller;

import com.fuyi.jwt.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @RequestMapping("/getUserById")
    public User getUserById() {
        return new User("123", "fuyi", "123", 1, "haha", null);
    }
}
