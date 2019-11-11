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
public class DruidMonitorStatViewServlet {
    /**
     * 是否启用StatViewServlet默认值true
     */
    private String enabled = "true";

    /**
     * URL Mappings映射
     */
    private String urlPattern = "/druid/*";

    /**
     * 白名单
     */
    private String allow = "";
    /**
     * IP黑名单(存在共同时，deny优先于allow)
     */
    private String deny = "";
    /**
     * 登录查看信息的账号
     */
    private String loginUsername = "siss";

    /**
     * 登录查看信息的密码
     */
    private String loginPassword = "admin";

    /**
     * 是否能够重置数据
     */
    private String resetEnable = "false";
}
