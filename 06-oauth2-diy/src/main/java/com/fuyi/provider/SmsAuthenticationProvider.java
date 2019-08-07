package com.fuyi.provider;

import com.fuyi.common.ResultCode;
import com.fuyi.entity.VerifyCode;
import com.fuyi.ex.AuthException;
import com.fuyi.token.SmsAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

public class SmsAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

	public SmsAuthenticationProvider(UserDetailsService UserDetailsService) {
		this.userDetailsService = UserDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

		String mobile = (String) authenticationToken.getPrincipal();
		UserDetails user = userDetailsService.loadUserByUsername(mobile);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format(ResultCode.USER_NOT_EXIST.getMsg(), mobile));
		}

		String codeInRequest = authenticationToken.getCode();
		if (StringUtils.isEmpty(codeInRequest)) {
			throw new AuthException(ResultCode.SMS_CODE_NOT_EXIST);
		}

		// 获取正确的验证码  123456
		VerifyCode codeInRepo = new VerifyCode("123456", 99999);
		if (codeInRepo == null || codeInRepo.getExpiredTime().compareTo(LocalDateTime.now()) < 0) {
			throw new AuthException(ResultCode.SMS_CODE_EXPIRED);
		}

		if (!codeInRepo.getCode().equalsIgnoreCase(codeInRequest)) {
			throw new AuthException(ResultCode.SMS_CODE_ERROR);
		}
		//验证码成功之后删除

		SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user, user.getAuthorities());
		
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
