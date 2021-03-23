package com.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.pojo.Hr;
import com.server.pojo.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功之后
 */
@Component
public class FormLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 获取用户信息
        Hr hr = (Hr) authentication.getPrincipal();
        // 将密码改为null
        hr.setPassword(null);
        RespBean ok = RespBean.ok("登录成功！", hr);
        // 将数据格式转换为json格式
        String s = new ObjectMapper().writeValueAsString(ok);
        out.write(s);
        out.flush();
        out.close();
    }
}
