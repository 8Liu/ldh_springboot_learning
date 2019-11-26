package com.liudehuang.shardingjdbc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class Merchant implements Serializable {

    private Long id;

    private Long merchantId;

    private String password;

    private String name;
}
