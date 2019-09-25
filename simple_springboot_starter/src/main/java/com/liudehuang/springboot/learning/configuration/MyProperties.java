package com.liudehuang.springboot.learning.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
@ConfigurationProperties(prefix = MyProperties.prefix)
public class MyProperties {

    public static final String prefix = "my";

    private String name = "test";
}
