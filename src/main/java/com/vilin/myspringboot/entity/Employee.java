package com.vilin.myspringboot.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    private Integer id;
    private String name;
    private String department;
    private String position;
    private Date hiredate;
    private Float salary;

    public Employee(Integer id, String name, String department, String position, String hiredate, Float salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        try {
            this.hiredate = new SimpleDateFormat("yyyy-MM-dd").parse(hiredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}

