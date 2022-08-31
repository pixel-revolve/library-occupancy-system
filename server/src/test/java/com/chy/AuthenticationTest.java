package com.chy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthenticationTest {

  //   private static AuthenticationManager am = new SampleAuthenticationManager();
  //
  //   @Test
  //   public void testAuthentication(){
  //       String name = "";
  //       String password = "";
  //       try {
  //
  //           // request就是第一步，使用name和password封装成为的token
  //           Authentication request = new UsernamePasswordAuthenticationToken(name, password);
  //           // 将token传递给Authentication进行验证
  //           Authentication result = am.authenticate(request);
  //           SecurityContextHolder.getContext().setAuthentication(result);
  //       }  catch (AuthenticationException e) {
  //           System.out.println("认证失败：" + e.getMessage());
  //       }
  //       System.out.println("认证成功，Security context 包含：" + SecurityContextHolder.getContext().getAuthentication());
  // }
  //
  //   class SampleAuthenticationManager implements AuthenticationManager {
  //       static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
  //
  //       static {
  //           AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
  //       }
  //       public Authentication authenticate(Authentication auth) throws AuthenticationException {
  //           if (auth.getName().equals(auth.getCredentials())) {
  //               return new UsernamePasswordAuthenticationToken(auth.getName(),
  //                       auth.getCredentials(), AUTHORITIES);
  //           }
  //           throw new BadCredentialsException("Bad Credentials");
  //       }
  //   }



}
