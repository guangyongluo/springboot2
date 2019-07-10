package com.vilin.myspringboot.mapper;

import com.vilin.myspringboot.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public User findById(Integer id);
    //mybatis使用Map对象包含多个参数
    public List<Map> findUsersByOptions(Map param);

    public void insert(User user);

    public void update(User user);

    public void delete(Integer id);
}
