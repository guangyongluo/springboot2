package com.vilin.myspringboot.repository;

import com.vilin.myspringboot.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//JpaRepository是SpringBoot为我们提供的简化类，默认提供了增删改查的具体实现
//我们只需要定义Repository接口就可以了，在SpringBoot启动的时候会自动帮我们生成具体的实现类，来实现CRUD方法
//JpaRepository的泛型参数为需要进行CRUD操作的实体类，和该实体类的主键
public interface DepartRepository extends JpaRepository<Depart, Integer> {

    //select * from dept where dname=...
    public List<Depart> findByDname(String dname);

    //select * from dept where deptno <= ? and dname like '%...%' order by dname asc;
    public List<Depart> findByDeptnoLessThanEqualAndDnameContainingOrderByDname(Integer deptno, String dname);

    //JPQL Java persistence query language持久化查询语言
    //1. 大多数情况下将*替换成别名
    //2. 表名改为类名
    //3. 字段名改为属性名
    //:dn是命名参数，其本质就是一个占位符，命名参数的格式为:参数名
    @Query("From Depart d where d.dname=:dn order by d.deptno desc")
    public List<Depart> findDeparts(@Param("dn") String dname);
}
