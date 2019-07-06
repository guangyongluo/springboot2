package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.Depart;
import com.vilin.myspringboot.entity.Employer;
import com.vilin.myspringboot.repository.DepartRepository;
import com.vilin.myspringboot.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

//使用RestController默认所有方法都返回JSON字符串，而不是跳转页面，我们不用再方法上写ResponseBody
@RestController
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private DepartRepository departRepository;

    @GetMapping("/{id}")
    public Employer findById(@PathVariable("id") Integer id){
        return employerRepository.findById(id).get();
    }

    @GetMapping("/add")
    public Employer addEmployer(){
        Employer employer = new Employer();
        employer.setEname("luowei");
        employer.setMgr(null);
        employer.setJob("Worker");
        employer.setHiredate(new Date());
        employer.setSal(35000f);
        employer.setComm(0f);
        Depart depart = departRepository.findById(20).get();
        employer.setDepart(depart);
        employerRepository.save(employer);
        return employer;
    }

    @GetMapping("/get")
    public List<Employer> getEmployerByDname(Integer deptno){
        return employerRepository.findEmployer(deptno);
    }
}
