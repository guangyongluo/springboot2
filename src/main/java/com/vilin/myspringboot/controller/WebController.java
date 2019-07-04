package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.Department;
import com.vilin.myspringboot.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@ConfigurationProperties(prefix = "app")
public class WebController {
    private Logger logger = LoggerFactory.getLogger(WebController.class);
    private List<Employee> emps = new ArrayList<Employee>();
    private List<Department> depts = new ArrayList<Department>();

    private String path = null;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    @PostMapping("/addemp")
    //MultipartFile是上传文件接口，对应了保存的临时文件，参数名与前端的name保持一致。
    //@RequestParam("photo")代表了photo参数对应于前端name=photo的file输入框
    public ModelAndView addEmployee(Employee emp, @RequestParam("photo")  MultipartFile photo){

        if(photo.isEmpty()) {
            throw new RuntimeException("请重新上传文件");
        }
        //获取原始文件名
        String filename = photo.getOriginalFilename();
        //String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));

        if(!suffix.equals(".PNG")){
            throw new RuntimeException("无效的图片格式");
        }

        //Spring提供了一个文件操作类FileCopyUtil
        try {
            FileCopyUtils.copy(photo.getInputStream(), new FileOutputStream(path + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        emp.setPhotoPath(path + filename);
        emps.add(emp);

        //页面重定项 redirect:跳转地址
        ModelAndView mav = new ModelAndView("redirect:/empinfo");
        return mav;
    }
}
