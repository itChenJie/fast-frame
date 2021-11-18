package com.common.multipledatasource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/11/15 3:10 下午
 **/
@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDb(String db){
        log.info("切换到{}数据源",db);
        contextHolder.set(db);
    }

    public static String getDb(){
        return contextHolder.get();
    }

    public static void clearDB(){
        contextHolder.remove();
    }
}
