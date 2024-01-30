package com.example.springsecuritystudy.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecuritystudy.domain.ResponseResult;
import com.example.springsecuritystudy.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        ResponseResult<?> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "No Permission");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
