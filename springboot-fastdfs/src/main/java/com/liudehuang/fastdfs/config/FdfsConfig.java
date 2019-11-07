package com.liudehuang.fastdfs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-07
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-07
 * @UpdateRemark:
 * @Version: 1.0
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FdfsConfig {
}
