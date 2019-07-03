package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.Department;
import com.vilin.myspringboot.entity.Dept;
import com.vilin.myspringboot.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private Logger logger = LoggerFactory.getLogger(WebController.class);
    private List<Employee> emps = new ArrayList<Employee>();
    private List<Department> depts = new ArrayList<Department>();

    public WebController(){
        emps.add(new Employee(7782, "ClARK", "RESEARCH", "DEVELOPER", "2014-12-01", 7780f) );
        emps.add(new Employee(7839, "KING", "SALES", "CFO", "2018-10-10", 8820f) );
        depts.add(new Department(10, "RESEARCH", "2017-10-07"));
        depts.add(new Department(20, "SALES", "2015-12-01"));
        depts.add(new Department(30, "ACCOUNTING", "2013-03-02"));
    }

    //RequestMethod.GET只有Get请求才能访问这个方法，如果是POST则会提示405错误
    @RequestMapping(value="/empinfo", method = RequestMethod.GET)
    public ModelAndView getEmpInfo(){
        //设置数据上下文，上下文数据就是页面中要读取的记录，在Spring MVC常用的设置上下文有三种：
        /**
         * 1. ModelAndView(推荐)
         * 2. Model
         * 3. WebRequest或者原生的HttpServletRequest对象(不推荐)
         */
        ModelAndView mav = new ModelAndView("employee_info");
        mav.addObject("emps", emps);
        return mav;
    }

    @RequestMapping(value = "/deptinfo", method = RequestMethod.GET)
    @ResponseBody //将返回值JSON化后送给浏览器，默认SpringBoot使用JSON序列化工具为Jackson
    //AJAX返回的是JSON数据，而不是跳转数据
    public List<Department> getDepts(){
        List<Department> newDepts = new ArrayList<Department>();
        newDepts.add(new Department(-1, "请选择", "1970-01-01"));
        newDepts.addAll(depts);
        return newDepts;
    }

    @GetMapping("/job")
    @ResponseBody
    public List<String> getJob(String dept){
        List<String> jobs = new ArrayList<String>();
        jobs.add("请选择");
        if(dept.equals("RESEARCH")){
            jobs.add("CTO");
            jobs.add("Programmer");
        }else if(dept.equals("SALES")){
            jobs.add("CSO");
            jobs.add("Sale");
        }else if(dept.equals("ACCOUNTING")){
            jobs.add("CFO");
            jobs.add("Cashier");
        }
        return jobs;
    }
}
