package com.vilin.myspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 热部署步骤：
 *
 * 1. spring.thymeleaf.cache=false 关闭页面缓存
 * 2. 在pom.xml中定义
 * <dependency>
 *      <groupId>org.springframework.boot</groupId>
 *      <artifactId>spring-boot-devtools</artifactId>
 *  </dependency>
 *      在maven-plugin中增加
 *  <configuration>
 *       <fork>true</fork>
 *  </configuration>
 *  3. 修改idea的设置，如果是eclipse第三部可省略
 *    file-->setting-->build-->complier--> build project automatically
 *    ctrl+shift+a 点击registry...  确保complier.automake.allow.when.app.running = true
 */
@Controller
public class TestController {
    @RequestMapping("/home")
    public String index(){
        return "home";
    }
}
