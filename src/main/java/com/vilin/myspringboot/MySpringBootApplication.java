package com.vilin.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//我是一个入口类，运行我就可以启动springboot应用，运行之后会自动扫描当前包和子包下
//可以被注入的类包括：@Repository, @Service, @Controller, @Component, ,@Entity
@SpringBootApplication
//在入口类启动时加载config.properties
@PropertySource("classpath:config.properties")
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class);
    }
}
