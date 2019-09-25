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
public class CreateIndexRequestParamParam extends IndexRequestParam {
    /**
     * 文档的属性
     */
    private Map<String,Object> map;
}
