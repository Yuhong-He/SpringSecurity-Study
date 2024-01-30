package com.example.springsecuritystudy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hello2")
    @PreAuthorize("hasAuthority('system:dept:list2')")
    public String hello2() {
        return "Hello World!";
    }

    @RequestMapping("/hello3")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello3() {
        return "Hello World!";
    }
}
