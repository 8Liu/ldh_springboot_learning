package com.liudehuang.shardingjdbc.config;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
public class MerchantIdPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> tableNameList, PreciseShardingValue<Long> shardingValue) {
        log.debug("merchantId 分表规则计算开始：请求参数：{}",shardingValue);
        for(String tableName:tableNameList){
            String merchantId = tableName.substring(tableName.lastIndexOf("_")+1);
            if (merchantId.equals(shardingValue.getValue().toString())){
                return tableName;
            }
        }
        return null;
    }


}
