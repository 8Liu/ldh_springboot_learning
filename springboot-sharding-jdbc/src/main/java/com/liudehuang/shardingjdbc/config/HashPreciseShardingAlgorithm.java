package com.liudehuang.shardingjdbc.config;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-25
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-25
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
public class HashPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    /**
     *
     * @param allTableNames 所有的表名称
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> allTableNames, PreciseShardingValue<String> preciseShardingValue) {
        log.debug("name hashcode取模 分表规则计算开始 . 请求参数:{}",preciseShardingValue);
        for (String name : allTableNames) {
            int shardingHash = Math.abs(preciseShardingValue.getValue().hashCode()); //计算hashCode绝对值
            if (name.endsWith(shardingHash % allTableNames.size() + "")) { //根据hashCode值对分表的size取模
                log.debug("name hashCode取模 分表规则匹配成功 . hashCode值 : {} , 取模值 : {} , 匹配的表 : {}" , shardingHash , shardingHash % allTableNames.size()  , name);
                return name;
            }
        }
        return null;
    }
}
