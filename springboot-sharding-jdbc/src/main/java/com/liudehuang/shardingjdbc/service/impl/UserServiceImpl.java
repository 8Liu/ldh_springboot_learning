package com.liudehuang.shardingjdbc.service.impl;

import com.alibaba.fastjson.JSON;
import com.liudehuang.shardingjdbc.entity.User;
import com.liudehuang.shardingjdbc.mapper.UserMapper;
import com.liudehuang.shardingjdbc.service.UserService;
import io.shardingjdbc.core.api.HintManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-25
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-25
 * @UpdateRemark:
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectSelective(User record) {
        log.debug("准备查询用户 , 请求参数 : {}", JSON.toJSONString(record));
        return userMapper.selectSelective(record);
    }

    @Override
    public List<User> selectSelectiveMaster(User record) {
        HintManager.getInstance().setMasterRouteOnly();// 强制路由主库
        log.debug("强制主库查询用户 , 请求参数 : {}", JSON.toJSONString(record));
        return userMapper.selectSelective(record);
    }

    @Override
    public int insertSelective(User record) {
        log.debug("准备创建用户 , 请求参数 : {}", JSON.toJSONString(record));
        Integer result = userMapper.insertSelective(record);
        log.debug("创建用户完成 , 影响条数 : {}" , result);
        return result;
    }
}
