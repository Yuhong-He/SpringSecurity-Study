package com.example.springsecuritystudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springsecuritystudy.domain.ResponseResult;
import com.example.springsecuritystudy.domain.User;

public interface LoginService extends IService<User> {
    ResponseResult<?> login(User user);

    ResponseResult<?> logout();
}
