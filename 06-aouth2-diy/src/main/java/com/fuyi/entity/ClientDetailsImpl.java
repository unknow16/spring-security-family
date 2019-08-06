package com.fuyi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.*;

/**
 * 获取用户信息
 *
 * @author xurenjie
 * @date on 2018-12-04 13:24
 */
@Data
@Builder
@ToString(callSuper = true)
@TableName("jr_client_details")
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsImpl implements Serializable {

    private static final long serialVersionUID = 3690855873346127925L;
    private String clientId;

    private String clientSecret;

    private Set<String> scope = Collections.emptySet();

    private Set<String> resourceIds = Collections.emptySet();

    private Set<String> authorizedGrantTypes = Collections.emptySet();

    private Set<String> webServerRedirectUri;

    private Set<String> autoapprove;

    private List<GrantedAuthority> authorities = Collections.emptyList();

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
}
