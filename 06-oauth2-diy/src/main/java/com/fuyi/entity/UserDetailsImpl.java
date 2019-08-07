package com.fuyi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author xurenjie
 * @date on 2018-11-07 14:00
 */
@Data
@ToString
@Builder
@TableName("jr_user_details")
public class UserDetailsImpl implements Serializable, UserDetails {
    private static final long serialVersionUID = 7489230799286954271L;

    /**
     * 用户id
     */
    @JsonProperty("userId")
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "email格式不正确")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1([3-9])\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    @JsonIgnore
    private String type;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
