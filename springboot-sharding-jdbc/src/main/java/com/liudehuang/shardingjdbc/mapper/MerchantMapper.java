package com.liudehuang.shardingjdbc.mapper;

import com.liudehuang.shardingjdbc.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
@Mapper
public interface MerchantMapper {
    /**
     * 根据主键查询商户信息
     * @param id
     * @return
     */
    Merchant selectByPrimaryKey(Long id);

    /**
     * 插入
     * @param merchant
     * @return
     */
    Integer insert(Merchant merchant);
}
