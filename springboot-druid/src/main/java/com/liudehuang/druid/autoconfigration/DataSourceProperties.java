package com.liudehuang.druid.autoconfigration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

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
@ConfigurationProperties(prefix = "spring")
public class DataSourceProperties {
    /**
     * 数据源基础配置信息
     */
    private Map<String,String> datasource;
}
