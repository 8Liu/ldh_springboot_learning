package com.liudehuang.druid.autoconfigration;

import lombok.Data;

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
public class DruidMonitorWebStatFilter {
    /**
     * 是否启用StatFilter默认值true
     */
    private String enabled = "true";

    /**
     * URL过滤规则
     */
    private String urlPattern = "/*";

    /**
     * 不需要忽略的格式信息
     */
    private String exclusions = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";

    private String sessionStatEnable = "";
    private String sessionStatMaxCount = "";
    private String principalSessionName = "";
    private String principalCookieName = "";
    private String profileEnable = "";
}
