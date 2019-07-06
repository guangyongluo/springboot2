package com.vilin.myspringboot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //告诉SpringBoot这是一个实体类，在SpringBoot启动的时候会加载这个类
@Table(name="dept") //Dept类对应dept表
public class Depart {

    @Id //说明这个属性是对应表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deptno")//deptno对应于dept表中的deptno属性，如果属性名与字段名相同可以省略@Column，不建议这么使用
    private Integer  deptno;

    private String dname;

    private String location;

    //在绝大多数情况下我们不配置OntToMany 1.数据获取效率差；2.会形成死循环
//    @OneToMany
//    @JoinColumn(name="deptno")
//    private List<Employer> emps = new ArrayList<Employer>();

    public Depart() {
    }

    public Depart(Integer deptno, String dname, String location) {
        this.deptno = deptno;
        this.dname = dname;
        this.location = location;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public List<Employer> getEmps() {
//        return emps;
//    }
//
//    public void setEmps(List<Employer> emps) {
//        this.emps = emps;
//    }
}
