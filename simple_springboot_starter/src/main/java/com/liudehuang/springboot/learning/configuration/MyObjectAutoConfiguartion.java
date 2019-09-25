package com.liudehuang.springboot.learning.configuration;

import com.liudehuang.springboot.learning.entity.School;
import com.liudehuang.springboot.learning.entity.Student;
import com.liudehuang.springboot.learning.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties(MyProperties.class)
public class MyObjectAutoConfiguartion {

    @Bean
    @ConditionalOnClass({Teacher.class, School.class})
    public Teacher teacher() {
        return new Teacher();
    }

    @Bean
    @ConditionalOnMissingBean
    public Student student(@Autowired MyProperties myProperties) {
        return new Student(myProperties.getName());
    }
}
