package com.chy.handler;

import com.alibaba.fastjson.JSON;
import com.chy.model.Result;
import com.chy.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登录成功处理器逻辑
 */
public class MyAuthSuccessHandler implements AuthenticationSuccessHandler {

   private JwtTokenUtil jwtTokenUtil;

   public MyAuthSuccessHandler(JwtTokenUtil jwtTokenUtil) {
      this.jwtTokenUtil = jwtTokenUtil;
   }

   @Override
   public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
      // 设定响应状态码为200
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      // 设定响应内容是utf-8编码的json类型
      httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
      httpServletResponse.setCharacterEncoding("utf-8");

      // 生成jwt，并放置到请求头中
      String jwt = jwtTokenUtil.generateToken(authentication.getName());
      System.out.println(jwt);
      httpServletResponse.setHeader(jwtTokenUtil.getHeader(),jwt);

      // 组装自己的结果对象
      Result result = new Result();
      result.setResultSuccess("登录成功！");
      // 序列化结果对象为JSON
      String resultJSON = JSON.toJSONString(result);
      // 写入响应体
      PrintWriter writer = httpServletResponse.getWriter();
      writer.write(resultJSON);
      writer.flush();
      writer.close();
   }

}
