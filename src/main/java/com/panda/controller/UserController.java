package com.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panda.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public void setUserservice(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/test")
    public String index() {
        return "index";
    }
}