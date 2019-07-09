package com.vilin.myspringboot.service;

import com.vilin.myspringboot.entity.User;
import com.vilin.myspringboot.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(Integer id){
        return userMapper.findById(id);
    }

    public List<Map> findUsersByOptions(String className, Float score){
        Map param = new HashMap();
        param.put("className", className);
        param.put("score", score);
        return userMapper.findUsersByOptions(param);
    }
}
