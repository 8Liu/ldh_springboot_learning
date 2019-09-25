package com.liudehuang.springboot.learning.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class Student {

    private String name;

    private String sex;

    private Integer age;

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }
}
