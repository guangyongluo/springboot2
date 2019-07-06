package com.vilin.myspringboot.repository;

import com.vilin.myspringboot.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    @Query("from Employer e where e.depart.deptno=:dn")
    public List<Employer> findEmployer(@Param("dn") Integer deptno);
}
