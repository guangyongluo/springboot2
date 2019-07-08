package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.Depart;
import com.vilin.myspringboot.entity.Employer;
import com.vilin.myspringboot.repository.DepartRepository;
import com.vilin.myspringboot.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
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

    @GetMapping("/imp")
    //在默认情况下，数据库事务作用范围是在JpaRepository的CRUD方法上。
    //save方法一旦执行马上成功提交
    //要保证数据的完成性，那就需要将事务提高到imp方法上，只需要在方法上增加注解@Transactional就可以了
    @Transactional //开启事务，imp方法运行成功提交，运行失败抛出RuntimeException及其子类
    //针对于使用注解的事务形式，也有一个名词叫做“声明式事务”
    public void imp(){
        for(int i = 0; i < 10; i++){
            if(i == 3){
                throw new RuntimeException("出错了");
            }
            Employer employer = new Employer();
            employer.setEname("lwei" + i);
            employer.setJob("Engineer");
            employer.setMgr(null);
            employer.setHiredate(new Date());
            employer.setSal(40000f);
            employer.setComm(0f);
            Depart depart = departRepository.findById(20).get();
            employer.setDepart(depart);
            employerRepository.saveAndFlush(employer);
        }
    }
}
