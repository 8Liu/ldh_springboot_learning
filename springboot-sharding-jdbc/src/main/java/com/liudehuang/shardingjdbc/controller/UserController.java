package com.liudehuang.shardingjdbc.controller;

import com.liudehuang.shardingjdbc.entity.User;
import com.liudehuang.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title 用户(User)表控制层
 * @author Xingbz
 * @createDate 2018-11-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("/selectSelective")
    public List<User> selectSelective(User record) {
        return userService.selectSelective(record);
    }


    @PostMapping("/insertSelective")
    public void insertSelective(@RequestBody User user){
        userService.insertSelective(user);
    }

}