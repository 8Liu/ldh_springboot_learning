package com.liudehuang.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-25
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-25
 * @UpdateRemark:
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan({"com.liudehuang.mybatis.mapper"})
public class MybatisApp {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class,args);
    }
}
