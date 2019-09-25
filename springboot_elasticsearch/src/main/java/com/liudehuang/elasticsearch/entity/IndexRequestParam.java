package com.liudehuang.elasticsearch.entity;

import lombok.Data;

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
public class IndexRequestParam {
    /**
     * 索引名称
     */
    protected String index;
    /**
     * 文档ID
     */
    protected String id;
}
