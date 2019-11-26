package com.liudehuang.shardingjdbc.service;

import com.liudehuang.shardingjdbc.entity.Merchant;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface MerchantService {

    Merchant selectById(Long id);

    int insert(Merchant param);
}
