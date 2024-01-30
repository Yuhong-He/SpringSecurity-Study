package com.example.springsecuritystudy.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecuritystudy.domain.ResponseResult;
import com.example.springsecuritystudy.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        ResponseResult<?> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "Username or password incorrect");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
