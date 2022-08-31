package com.chy;

import com.chy.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import net.bytebuddy.build.Plugin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class JwtTest {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * 创建token(自定义声明)
     */
    @Test
    public void testJwtBuilderHasClaims(){
        long now=System.currentTimeMillis();
        long exp = now + 60 * 1000;

        JwtBuilder jwtBuilder=Jwts.builder()
                //设置id,{"jti":"888"}
                .setId("888")
                //签发用户{"sub":"Rose"}
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"xxxx")
                //自定义申明，参数是map
                //.addClaims();
                .claim("logo","xxx.jpg")
                .claim("名言","我是一个飞飞鱼");


        //生成token
        String token=jwtBuilder.compact();
        System.out.println(token);
        String[] split=token.split("\\.");
        //头部
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        //负载
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        //签名,乱码
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));

    }

    /**
     * 解析token（自定义声明）
     */
    @Test
    public void parseTokenHasClaims(){

        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNjM4MDc3MzQ1LCJsb2dvIjoieHh4LmpwZyIsIuWQjeiogCI6IuaIkeaYr-S4gOS4qumjnumjnumxvCJ9.oQ34MjgnSWD9O7213J2yUaXIP4ZAkzh8cmaIA2DwE74";

        // 获得claims(荷载)
        Claims claims = Jwts.parser()
                .setSigningKey("xxxx")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("jti:"+claims.getId());
        System.out.println("sub:"+claims.getSubject());
        System.out.println("iat:"+claims.getIssuedAt());

        System.out.println("logo:"+claims.get("logo"));
        System.out.println("名言:"+claims.get("名言"));

    }


    @Test
    public void generateJwtToken(){
        String username="zl";
        String token=jwtTokenUtil.generateToken(username);
        System.out.println(token);
    }

    @Test
    public void getClaimByToken(){
        String jwt="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6bCIsImlhdCI6MTYzODQxMTE3NywiZXhwIjoxNjM5MDE1OTc3fQ.7HIoQHbqaHxX57QwLjbt1nnjomkBH9LAGYjkRBjrIAaPMRrsQs48cZjpW7fGaiREu5AFLCx2LGufCnKI5UIJzA";

        Claims claim = jwtTokenUtil.getClaimByToken(jwt);

        System.out.println(claim.getSubject());
        System.out.println(claim.getId());


    }

}
