package com.fuyi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class Main {

    public static void main(String[] args) throws Exception {
       /* String compactJws = Jwts.builder()
                .setSubject("fuyi")
                .signWith(SignatureAlgorithm.HS512, "salt")
                .compact();*/
        //System.out.println(compactJws);

        Map map = new HashMap();
        map.put("sub", "haha");
        map.put("name", "fff");

        String jws = Jwts.builder()
                .setClaims(map)
                .setExpiration(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-1-18 12:12:00"))
                .signWith(SignatureAlgorithm.HS512, "salt")
                .compact();
        System.out.println(jws);

        Claims claims = Jwts.parser().setSigningKey("salt").parseClaimsJws(jws).getBody();
        System.out.println();


    }

    Claims getClaimsFromToken(String token, String secret) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            String name = claims.get("name").toString();
            System.out.println(name);
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
