package com.common.multipledatasource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description
 * 动态数据源
 * @Author ChenWenJie
 * @Data 2021/11/15 3:38 下午
 **/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DataSourceContextHolder.getDb());

        return DataSourceContextHolder.getDb();
    }
}
