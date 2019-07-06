package com.vilin.myspringboot.controller;

import com.vilin.myspringboot.entity.Depart;
import com.vilin.myspringboot.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/depart")
public class DepartController {

    @Autowired
    private DepartRepository departRepository = null;

    @GetMapping("/{id}")
    //@PathVariable将路劲中符合要求的部分，注入到对应的参数中，这种形式称为“路劲变量”
    @ResponseBody
    public Depart findById(@PathVariable("id") Integer id){
        //Optional是实体类的包装类，用于判断对象是否存在
        Optional<Depart> op = departRepository.findById(id);
        //isPresent()如果传入的id有对应的数据返回true，否则返回true
        Depart dept = null;
        if(op.isPresent() == true){
            dept = op.get();
        }
        return dept;
    }

    @GetMapping("/addDept")
    @ResponseBody
    public Depart add(){
        Depart depart = new Depart();
        depart.setDname("Tranning");
        depart.setLocation("New York");
        departRepository.save(depart);
        return depart;
    }

    @GetMapping("/updateDept")
    @ResponseBody
    public Depart update(){
        Depart depart = new Depart();
        depart.setDeptno(41);
        depart.setDname("TECHNOLOGY");
        depart.setLocation("SILICON VALLY");
        departRepository.save(depart);
        return depart;
    }

    @GetMapping("/deleteDept")
    public void delete(){
        Depart depart = new Depart();
        depart.setDeptno(41);
        departRepository.delete(depart);
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Depart> findDepartByName(String name){
        List<Depart> list = departRepository.findByDname(name);
        return list;
    }

    @GetMapping("/find/departs")
    @ResponseBody
    public List<Depart> findDeparts(String name){
        List<Depart> list = departRepository.findDeparts(name);
        return list;
    }

    @GetMapping("/JPA/find")
    @ResponseBody
    public List<Depart> findDepartsByJPARespository(){
        List<Depart> list = departRepository.findByDeptnoLessThanEqualAndDnameContainingOrderByDname(50, "T");
        return list;
    }
}
