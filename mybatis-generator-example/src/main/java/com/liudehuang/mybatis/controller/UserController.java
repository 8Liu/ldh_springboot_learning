package com.liudehuang.mybatis.controller;

import com.liudehuang.mybatis.domain.User;
import com.liudehuang.mybatis.domain.UserExample;
import com.liudehuang.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-25
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-25
 * @UpdateRemark:
 * @Version: 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUser")
    public List<User> getUser(){
        UserExample userExample = new UserExample();
        userExample.setOffset(0);
        userExample.setRows(20);
        userExample.createCriteria().andNicknameLike("%TH%");
        List<User> users = userMapper.selectByExampleSelective(userExample,User.Column.username,User.Column.password,
                User.Column.nickname,User.Column.id, User.Column.avatar);
        return users;
    }
}
