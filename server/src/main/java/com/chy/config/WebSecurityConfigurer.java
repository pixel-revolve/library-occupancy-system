package com.chy.config;

import com.chy.filter.JwtAuthenticationFilter;
import com.chy.filter.MyAuthFilter;
import com.chy.filter.WebSecurityCorsFilter;
import com.chy.handler.*;
import com.chy.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 配置密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }


    /**
     * 配置自定义用户名密码拦截器为Bean
     */
    @Bean
    public UsernamePasswordAuthenticationFilter myAuthFilter() throws Exception {
        UsernamePasswordAuthenticationFilter myAuthFilter = new MyAuthFilter();
        // 注意，因为是自定义登录拦截器，所以登录接口地址要在此配置！
        myAuthFilter.setFilterProcessesUrl("/api/user/login");
        // 设定为自定义的登录成功/失败处理器
        myAuthFilter.setAuthenticationSuccessHandler(new MyAuthSuccessHandler(new JwtTokenUtil()));
        myAuthFilter.setAuthenticationFailureHandler(new MyAuthFailureHandler());
        myAuthFilter.setAuthenticationManager(authenticationManagerBean());
        return myAuthFilter;
    }

    /**
     * 配置安全拦截策略
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        httpSecurity.addFilter(jwtAuthenticationFilter());

        // 设定认证拦截器
        httpSecurity.authorizeRequests()
                .antMatchers("/api/user/login").hasAnyRole("STUDENT","ADMIN")
                .antMatchers("/api/user/updateUserInfo").hasAnyRole("STUDENT","ADMIN")
                .antMatchers("/api/user/**").hasAnyRole("STUDENT","ADMIN")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                //放行头像服务
                .antMatchers("/api/avatar/**").permitAll()
                // 需要登录后才能获取用户信息
                .antMatchers("/api/user/login","/api/user/isLogin").authenticated();

        // 自定义退出登录url和配置自定义的登出成功处理器
        httpSecurity.logout().logoutUrl("/api/user/logout").logoutSuccessHandler(new MyLogoutSuccessHandler(new JwtTokenUtil()));
        // 关闭csrf
        httpSecurity.csrf().disable();
        // 设定自己的登录认证拦截器
        httpSecurity.addFilterAt(myAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        // 设定为自定义的权限不足处理器
        httpSecurity.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
    }
}
