package com.example.springsecuritystudy;

import com.example.springsecuritystudy.mapper.MenuMapper;
import com.example.springsecuritystudy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityStudyApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234"));
    }

    @Test
    void testMapper() {
        System.out.println(menuMapper.selectPermsByUserId(3L));
    }

}
