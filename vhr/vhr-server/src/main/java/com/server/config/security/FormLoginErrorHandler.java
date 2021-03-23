package com.server.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.pojo.RespBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败之后
 */
@Component
public class FormLoginErrorHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RespBean respBean = RespBean.error(exception.getMessage());
        // 判断登录失败原因
        if (exception instanceof LockedException){
            respBean.setMsg("账户被锁定，请联系管理员！");
        }
        if (exception instanceof CredentialsExpiredException){
            respBean.setMsg("密码过期，请联系管理员!");
        }
        if (exception instanceof AccountExpiredException){
            respBean.setMsg("账户过期，请联系管理员!");
        }
        if (exception instanceof DisabledException){
            respBean.setMsg("账户被禁用，请联系管理员!");
        }
        if (exception instanceof BadCredentialsException){
            respBean.setMsg("用户名或者密码输入错误，请重新输入!");
        }
        String s = new ObjectMapper().writeValueAsString(respBean);
        out.write(s);
        out.flush();
        out.close();
    }
}
