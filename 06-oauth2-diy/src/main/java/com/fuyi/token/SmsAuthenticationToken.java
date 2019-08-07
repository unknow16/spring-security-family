package com.fuyi.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SmsAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -152878376810273171L;
	private final Object principal;
	private String code;

	public SmsAuthenticationToken(Object mobile, String code) {
		super(null);
		this.principal = mobile;
		this.code = code;
		setAuthenticated(false);
	}

	public SmsAuthenticationToken(Object userDetails, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = userDetails;
		super.setAuthenticated(true); // must use super, as we override
	}

	public Object getPrincipal() {
		return this.principal;
	}

	public String getCode(){
		return this.code;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}

	@Override
	public Object getCredentials() {
		return null;
	}
}
