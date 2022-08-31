package com.chy.handler;

import com.alibaba.fastjson.JSON;
import com.chy.model.Result;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义权限不足处理器
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

   @Override
   public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
      // 设定响应状态码为403
      httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
      // 设定响应内容是utf-8编码的json类型
      httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
      httpServletResponse.setCharacterEncoding("utf-8");
      // 组装自己的结果对象
      Result result = new Result();
      result.setResultFailed("权限不足！请联系管理员！");
      // 序列化结果对象为JSON
      String resultJSON = JSON.toJSONString(result);
      // 写入响应体
      PrintWriter writer = httpServletResponse.getWriter();
      writer.write(resultJSON);
      writer.flush();
      writer.close();
   }

}
