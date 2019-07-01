package com.vilin.myspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MyController {

    @RequestMapping("/out")
    @ResponseBody
    public String out(){
        return "success";
    }

    @RequestMapping("/")
    public String index(){
        //访问idx.html
        return "idx";
    }

    @RequestMapping("/date")
    @ResponseBody //可以帮我们将返回的对象JSON序列化
    public Date date(Date date){
        return date;
    }
}
