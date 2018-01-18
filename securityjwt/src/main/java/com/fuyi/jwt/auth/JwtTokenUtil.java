package com.fuyi.jwt.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class JwtTokenUtil {

    private static final String CLAIMS_KEY_USERNAME = "sub";
    private static final String CLAIMS_KEY_CREATED = "created";

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String authToken) {
        return Jwts.parser().setSigningKey("salt").parseClaimsJws(authToken).getBody().getSubject();
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        String usernameFromToken = getUsernameFromToken(authToken);
        String username = userDetails.getUsername();
        return username.equals(usernameFromToken);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIMS_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
