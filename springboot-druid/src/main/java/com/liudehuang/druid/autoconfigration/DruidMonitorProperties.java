package com.liudehuang.druid.autoconfigration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-09
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-09
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "druid.wiki")
public class DruidMonitorProperties {
    /**
     * WebStatFilter配置，说明请参考Druid Wiki，配置_配置WebStatFilter
     */
    private DruidMonitorWebStatFilter webStatFilter = new DruidMonitorWebStatFilter();

    /**
     * StatViewServlet配置，说明请参考Druid Wiki，配置_StatViewServlet配置
     */
    private DruidMonitorStatViewServlet statViewServlet = new DruidMonitorStatViewServlet();
}
