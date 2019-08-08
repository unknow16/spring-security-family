package com.fuyi.jwt.auth;

import com.fuyi.jwt.domain.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}