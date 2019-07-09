package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.User;
import com.vilin.myspringboot.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/user/{id}")
    public User findByid(@PathVariable("id") Integer id){
        return userService.findById(id);
    }

    @RequestMapping("/user/list")
    public List<Map> findUsersByOptions(String className, Float score){
        List<Map> list = userService.findUsersByOptions(className, score);
        return list;
    }
}
