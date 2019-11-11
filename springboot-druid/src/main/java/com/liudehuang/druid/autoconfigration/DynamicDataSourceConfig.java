package com.liudehuang.druid.autoconfigration;

import com.liudehuang.druid.utils.DataSourcePropertiesUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-11
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-11
 * @UpdateRemark:
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class,MybatisProperties.class})
public class DynamicDataSourceConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private MybatisProperties mybatisProperties;

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource()throws Exception{
        return DataSourcePropertiesUtil.convertDataSource(dataSourceProperties);
    }

    /**
     * 　配置mybatis的sqlSession连接动态数据源
     *
     * @param dynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean
    @ConditionalOnMissingBean(SqlSessionFactory.class)
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);

        if (!StringUtils.isEmpty(mybatisProperties.getTypeAliasesPackage())) {
            sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        }

        // 此处设置为了解决找不到mapper文件的问题
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mybatisProperties.getMapperLocations()));

        if (!StringUtils.isEmpty(mybatisProperties.getConfigLocation())) {
            sqlSessionFactoryBean.setConfigLocation(resourcePatternResolver.getResource(mybatisProperties.getConfigLocation()));
        }
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 将动态数据源添加到事务管理器中
     *
     * @param dynamicDataSource
     * @return the platform transaction manager
     */
    @Bean
    @ConditionalOnMissingBean(PlatformTransactionManager.class)
    public PlatformTransactionManager platformTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
