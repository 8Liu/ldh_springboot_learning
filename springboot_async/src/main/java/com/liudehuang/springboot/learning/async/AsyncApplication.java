package com.liudehuang.springboot.learning.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class,args);
    }
}
