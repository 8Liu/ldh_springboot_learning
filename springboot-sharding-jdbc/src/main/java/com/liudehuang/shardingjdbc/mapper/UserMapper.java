package com.liudehuang.shardingjdbc.mapper;

import com.liudehuang.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface UserMapper {

    /**
     * @title 根据实体查询多条记录
     */
    List<User> selectSelective(User record);


    /**
     * @title 动态新增记录
     */
    int insertSelective(User record);
}
