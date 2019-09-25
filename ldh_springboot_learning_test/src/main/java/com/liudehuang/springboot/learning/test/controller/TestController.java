package com.liudehuang.springboot.learning.test.controller;

import com.liudehuang.springboot.learning.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@CrossOrigin
@RestController
public class TestController {
    @Autowired
    private Student student;

    @GetMapping("/test")
    public String test(){
        return student.getName();
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
