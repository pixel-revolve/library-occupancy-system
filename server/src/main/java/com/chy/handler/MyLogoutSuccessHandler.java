package com.chy.handler;

import com.alibaba.fastjson.JSON;
import com.chy.model.Result;
import com.chy.utils.JwtTokenUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登出成功处理器
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

   private JwtTokenUtil jwtTokenUtil;

   public MyLogoutSuccessHandler(JwtTokenUtil jwtTokenUtil){this.jwtTokenUtil=jwtTokenUtil;}

   @Override
   public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
      // 设定响应状态码为200
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      // 设定响应内容是utf-8编码的json类型
      httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
      httpServletResponse.setCharacterEncoding("utf-8");

      if (authentication != null) {//对先前的认证进行Context的清除
         new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
      }

      httpServletResponse.setHeader(jwtTokenUtil.getHeader(), "");//将header去除

      // 组装结果
      Result result = new Result();
      result.setResultSuccess("退出成功！", null);
      // 序列化结果对象为JSON
      String resultJSON = JSON.toJSONString(result);
      // 写入响应体
      PrintWriter writer = httpServletResponse.getWriter();
      writer.write(resultJSON);
      writer.flush();
      writer.close();
   }

}

