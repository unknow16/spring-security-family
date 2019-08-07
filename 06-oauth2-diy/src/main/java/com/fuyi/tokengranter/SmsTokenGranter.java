package com.fuyi.tokengranter;

import com.fuyi.common.ResultCode;
import com.fuyi.ex.AuthException;
import com.fuyi.token.SmsAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

public class SmsTokenGranter extends AbstractTokenGranter {

	private static final String GRANT_TYPE = "sms";

	private AuthenticationManager authenticationManager;

    public SmsTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

	public SmsTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
		super(tokenServices, clientDetailsService, requestFactory, grantType);
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Authentication userAuth;
        String mobile;
        try {
            // 1. 获取参数
            Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
            mobile = parameters.get("mobile");
            String codeInRequest = parameters.get("code");

            if (StringUtils.isEmpty(mobile)) {
                throw new AuthException(ResultCode.MOBILE_NOT_EXIST);
            }

            userAuth = new SmsAuthenticationToken(mobile, codeInRequest);

            // 2. 进行用户认证
			userAuth = authenticationManager.authenticate(userAuth);
		}
		catch (AuthenticationException ase) {
			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
			throw new InvalidGrantException(ase.getMessage());
		}

		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException(ResultCode.USER_AUTHENCATION_ERROR.getMsg());
		}
		
		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		return new OAuth2Authentication(storedOAuth2Request, userAuth);
	}
}
