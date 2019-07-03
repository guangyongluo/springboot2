package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.Department;
import com.vilin.myspringboot.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
