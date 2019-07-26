package com.fuyi.jwt.auth;

import com.fuyi.jwt.domain.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIMS_KEY_USERNAME = "sub";
    private static final String CLAIMS_KEY_CREATED = "created";

    /**
     * 配置文件中过期时间，单位秒
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 配置文件中的jwt秘钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 从jwt中获取username
     * @param authToken
     * @return
     */
    public String getUsernameFromToken(String authToken) {
        String username;
        try {
            username = getClaimsFromToken(authToken).getSubject();
        } catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 从jwt中获取创建时间
     * @param authToken
     * @return
     */
    public Date getCreatedDateFromToken(String authToken) {
        Date date;
        try {
            date = new Date((Long)getClaimsFromToken(authToken).get(CLAIMS_KEY_CREATED));
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 从jwt中获取过期时间
     * @param authToke
     * @return
     */
    public Date getExpirationDateFromToken(String authToke) {
        Date date;
        try {
            date = getClaimsFromToken(authToke).getExpiration();
        }catch (Exception e){
            date = null;
        }
        return date;
    }

    /**
     * 校验token
     * 1.用户名一至
     * 2.当前时间在jwt过期时间之前
     * 3.密码上次被重置时间在jwt创建之前
     *
     * @param authToken
     * @param userDetails
     * @return
     */
    public boolean validateToken(String authToken, UserDetails userDetails) {
        JwtUser jwtUser = (JwtUser) userDetails;
        final String usernameFromToken = getUsernameFromToken(authToken);
        final Date createdDate = getCreatedDateFromToken(authToken);
        return usernameFromToken.equals(jwtUser.getUsername())
                && !isTokenExpired(authToken)
                && !isCreatedBeforeLastPasswordReset(createdDate, jwtUser.getLastPasswordResetDate());
    }

    /**
     * 生成jwt
     * 数据声明claims中包含sub，created, expiration
     * 分别为用户名，创建时间，过期时间
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CLAIMS_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIMS_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * token是否能被刷新
     * 1.上次密码重置时间在创建token时间之前
     * 2.token没过期
     * @param authToken
     * @param lastPasswordResetDate
     * @return
     */
    public boolean canRefreshToken(String authToken, Date lastPasswordResetDate){
        boolean flag;
        try {
            final Date createdDate = getCreatedDateFromToken(authToken);
            return !isCreatedBeforeLastPasswordReset(createdDate, lastPasswordResetDate)
                    && !isTokenExpired(authToken);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 刷新token
     * 1.更新token的创建时间
     * 2.更新过期时间
     * @param authToken
     * @return
     */
    public String refreshToken(String authToken) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(authToken);
            claims.put(CLAIMS_KEY_CREATED, new Date()); //更新创建时间
            refreshedToken = generateToken(claims);
        } catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }


    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String authToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(authToken).getBody();
    }

    /**
     * Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
     */
    private boolean isTokenExpired(String authToken) {
        Date expirationDate = getExpirationDateFromToken(authToken);
        return expirationDate.before(new Date());
    }

    private boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset){
        return created.before(lastPasswordReset);
    }
}
