package com.panda.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.panda.service.UserService;
public class UserTest {
private UserService userService;

    @Before
    public void before(){                                                                    
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("UserService");
    }

    @Test
    public void addUser(){
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("id", 1111);
        paramMap.put("password", 1111);
        paramMap.put("status", 1111);
        paramMap.put("nickname", 1111);
        paramMap.put("createTime", new Date());

        System.out.println(userService.insertUser(paramMap));
    }
}