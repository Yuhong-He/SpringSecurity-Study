package com.example.springsecuritystudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecuritystudy.domain.LoginUser;
import com.example.springsecuritystudy.domain.ResponseResult;
import com.example.springsecuritystudy.domain.User;
import com.example.springsecuritystudy.mapper.UserMapper;
import com.example.springsecuritystudy.service.JwtService;
import com.example.springsecuritystudy.service.LoginService;
import com.example.springsecuritystudy.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    private final JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<?> login(User user) {
        // 1. AuthenticationManager
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("Login Failed");
        }

        // 2. Generate JWT
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = jwtService.generateToken(id);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 3. Store in Redis
        redisCache.setCacheObject("Login:" + id, loginUser);
        return new ResponseResult<>(200, "Login Success", map);
    }

    @Override
    public ResponseResult<?> logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisCache.deleteObject("Login:" + id);
        return new ResponseResult<>(200, "Logout Success");
    }
}
