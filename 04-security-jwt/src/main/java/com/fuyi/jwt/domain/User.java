package com.fuyi.jwt.domain;

import java.util.List;

/**
 * 数据库中查询出来的用户对像
 * Created by Administrator on 2018/1/18 0018.
 */
public class User {

    private String id;
    private String username;
    private String password;
    private Integer status;
    private String desc;
    private List<String> roles;

    public User() {}

    public User(String id, String username, String password, Integer status, String desc, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.desc = desc;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
