package com.vilin.myspringboot.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Department {
    private Integer id;
    private String name;
    private Date time;

    public Department(Integer id, String name, String time) {
        this.id = id;
        this.name = name;
        try {
            this.time = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
