package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.User;
import com.vilin.myspringboot.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @RequestMapping("/user/insert")
    public User insert(){
        User user = new User();
        user.setName("罗启石");
        user.setSex("男");
        user.setScore(100f);
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-05"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setClassId(1);
        userService.insert(user);
        return user;
    }


    @RequestMapping("/user/update")
    public void update(){
        User user = userService.findById(1);
        user.setScore(user.getScore() * 2);
        userService.update(user);
    }

    @RequestMapping("/user/delete")
    public void delete(Integer id){
        userService.delete(id);
    }
}
