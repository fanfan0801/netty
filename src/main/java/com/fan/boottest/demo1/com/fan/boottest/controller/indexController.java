package com.fan.boottest.demo1.com.fan.boottest.controller;

import com.fan.boottest.demo1.com.fan.boottest.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @RequestMapping("/index")
    public String index(){
        return "你好";
    }
    @Autowired
    User user;
    @RequestMapping("/user")
    public User user(){

        return user;
    }
}
