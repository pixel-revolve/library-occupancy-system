package com.chy.utils;


import io.jsonwebtoken.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT生成令牌、验证令牌、获取令牌
 */

@Getter
@Setter
@Component
public class JwtTokenUtil {

    private long expire=604800; // 7天，秒单位

    private String secret="asdjigu8r972855mksjfsgst95252451";// 密钥随便填的32位字符串

    private String header="Authorization";

    // @PostConstruct
    // public void init(){
    //     header="Authorization";
    //     expire= 604800; // 7天，秒单位
    //     secret= "asdjigu8r972855mksjfsgst95252451"; // 密钥随便填的32位字符串
    // }

    /**
     * 生成令牌
     * @param  username
     * @return 令牌
     */
    public String generateToken(String username) {

        Date nowDate=new Date();
        Date expireDate=new Date(nowDate.getTime()+1000*expire);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)//7天过期
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 解析jwt
     * @param jwt
     * @return Claims
     */
    public Claims getClaimByToken(String jwt){

        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {//返回空则不合法则是异常的
            return null;
        }

    }

    // jwt是否过期
    public boolean isTokenExpired(Claims claims){
        // 判断过期时间是否在当前时间之前,是则说明已经过期了
        return claims.getExpiration().before(new Date());
    }


}