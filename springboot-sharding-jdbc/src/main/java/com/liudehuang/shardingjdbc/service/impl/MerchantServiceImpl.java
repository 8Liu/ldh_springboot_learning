package com.liudehuang.shardingjdbc.service.impl;

import com.alibaba.fastjson.JSON;
import com.liudehuang.shardingjdbc.annotation.MchDataSource;
import com.liudehuang.shardingjdbc.entity.Merchant;
import com.liudehuang.shardingjdbc.mapper.MerchantMapper;
import com.liudehuang.shardingjdbc.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Merchant selectById(Long id) {
        return merchantMapper.selectByPrimaryKey(id);
    }

    @MchDataSource
    @Override
    public int insert(Merchant param) {
        log.debug("准备创建用户 , 请求参数 : {}", JSON.toJSONString(param));
        Integer result = merchantMapper.insert(param);
        log.debug("创建用户完成 , 影响条数 : {}" , result);
        return result;
    }
}
