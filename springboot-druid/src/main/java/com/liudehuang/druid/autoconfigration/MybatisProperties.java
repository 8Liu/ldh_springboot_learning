package com.liudehuang.druid.autoconfigration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties {
    private String typeAliasesPackage;

    /**
     * 默认mapper xml文件存放处
     */
    private String mapperLocations = "classpath:mapper/*/*Mapper.xml";
    /**
     * mybatis配置文件存放地
     */
    private String configLocation;
}
