package com.vilin.myspringboot;

import com.vilin.myspringboot.common.filter.AccessRecorderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

//我是一个入口类，运行我就可以启动springboot应用，运行之后会自动扫描当前包和子包下
//可以被注入的类包括：@Repository, @Service, @Controller, @Component, ,@Entity
@SpringBootApplication
//在入口类启动时加载config.properties
@PropertySource("classpath:config.properties")
public class MySpringBootApplication {

    //注册Filter
    //@Bean会将方法中的返回对象在SpringBoot启动的时候放入IOC容器
    @Bean
    public FilterRegistrationBean filterRegiste(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //创建并注册一个过滤器
        registrationBean.setFilter(new AccessRecorderFilter());
        //对所有请求进行拦截
        registrationBean.addUrlPatterns("/*");
        //设置过滤器的名字
        registrationBean.addServletNames("AccessRecorder");
        //设置排序，如果系统中有多个过滤器，order就决定哪个过滤器先执行，数字越小越靠前执行
        registrationBean.setOrder(1);
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class);
    }
}
