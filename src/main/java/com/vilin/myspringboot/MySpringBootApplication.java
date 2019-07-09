package com.vilin.myspringboot;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.vilin.myspringboot.common.filter.AccessRecorderFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//MapperScan注解会在springboot启动的时候扫描mapper包，并根据xml自动生成对应的实现类
@MapperScan("com.vilin.myspringboot.mapper")
//我是一个入口类，运行我就可以启动springboot应用，运行之后会自动扫描当前包和子包下
//可以被注入的类包括：@Repository, @Service, @Controller, @Component, ,@Entity
@SpringBootApplication
//在入口类启动时加载config.properties
@PropertySource("classpath:config.properties")
public class MySpringBootApplication {

    //手动初始化DruidDataSource对象
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidSource(){
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    //注册后台的Servlet Bean，用于显示后台界面
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建StatViewServlet绑定到/druid路径下
        //运行后可以通过/druid访问druid控制台
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> param = new HashMap<String, String>();
        param.put("loginUsername","admin");
        param.put("loginPassword","123456");
        param.put("allow","");
        servletRegistrationBean.setInitParameters(param);
        return servletRegistrationBean;
    }

    //用于监听应用数据，filter用于收集数据，servlet用于展现数据
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> param = new HashMap<String, String>();
        param.put("exclusions", "*.js,*.css");
        filterRegistrationBean.setInitParameters(param);
        return filterRegistrationBean;
    }

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
