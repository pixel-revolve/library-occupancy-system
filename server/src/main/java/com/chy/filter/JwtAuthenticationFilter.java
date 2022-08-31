package com.chy.filter;

import cn.hutool.core.util.StrUtil;
import com.chy.dao.UserDao;
import com.chy.service.UserService;
import com.chy.service.impl.MyUserDetailServiceImpl;
import com.chy.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    MyUserDetailServiceImpl userDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt=request.getHeader(jwtTokenUtil.getHeader());
        if(StrUtil.isBlankOrUndefined(jwt)){
            chain.doFilter(request,response);

            return;
        }
        Claims claim = jwtTokenUtil.getClaimByToken(jwt);

        if(claim==null){
            throw new JwtException("token 异常");
        }

        if(jwtTokenUtil.isTokenExpired(claim)){
            throw new JwtException("token 已过期");
        }

        String username=claim.getSubject();
        //获得用户权限等信息

        com.chy.pojo.User user = userDao.getUserByName(username);//获得用户
        UserDetails userDetails = userDetailService.loadUserByUsername(username);//获得userDetails

        //将用户名和权限封装成我们的token信息
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetailService.getUserAuthority(user.getId()));
        // System.out.println(SecurityContextHolder.getContext().getAuthentication());

        //交给我们的security的上下文中去进行设置,这样就完成了我们的一个security的token认证的过程
        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        chain.doFilter(request,response);
    }

}
