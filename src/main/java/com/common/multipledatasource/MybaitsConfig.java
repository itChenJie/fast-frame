package com.common.multipledatasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/11/15 3:39 下午
 **/
//@Configuration
//@MapperScan(basePackages = "com.admin.dao")
public class MybaitsConfig {
    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    @Bean(name = "shop")
    @ConfigurationProperties(prefix = "spring.datasource.shop") // application.properteis中对应属性的前缀
    public DataSource shopDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean(name = "dynamicDS")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //codex 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(shopDataSource());

        //codex 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(16);
        dsMap.put(DataSourceEnum.SHOP.getDataSource(), shopDataSource());
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;

    /**
     * 配置 SqlSessionFactoryBean
     *
     * @return the sql session factory bean
     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setMapperLocations(resolveMapperLocations(mapperLocations.split(",")));
        return sqlSessionFactoryBean.getObject();
    }

    private Resource[] resolveMapperLocations(String[] mapperLocations) {
        List<Resource> resources = new ArrayList<Resource>();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                resources.addAll(Arrays.asList(getResources(mapperLocation)));
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
