package com.liudehuang.easyexcel.learning.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-10-31
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-10-31
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class User {
    @ExcelProperty("用户ID")
    private String uid;
    @ExcelProperty("用户名")
    private String userName;
    @ExcelProperty("用户密码")
    private String password;
    @ExcelProperty("学校")
    private String school;
}
