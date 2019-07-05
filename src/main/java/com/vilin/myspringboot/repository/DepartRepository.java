package com.vilin.myspringboot.repository;

import com.vilin.myspringboot.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository是SpringBoot为我们提供的简化类，默认提供了增删改查的具体实现
//我们只需要定义Repository接口就可以了，在SpringBoot启动的时候会自动帮我们生成具体的实现类，来实现CRUD方法
//JpaRepository的泛型参数为需要进行CRUD操作的实体类，和该实体类的主键
public interface DepartRepository extends JpaRepository<Depart, Integer> {

}
