package com.liudehuang.shardingjdbc.service;

import com.liudehuang.shardingjdbc.entity.User;

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
public interface UserService {
    List<User> selectSelective(User record);

    List<User> selectSelectiveMaster(User record);

    int insertSelective(User record);
}
