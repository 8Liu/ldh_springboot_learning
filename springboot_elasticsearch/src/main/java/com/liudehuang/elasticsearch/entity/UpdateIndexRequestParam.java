package com.liudehuang.elasticsearch.entity;

import lombok.Data;

import java.util.Map;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-24
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-24
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class UpdateIndexRequestParam extends IndexRequestParam{
    private Map<String, Object> updateMap;
}
