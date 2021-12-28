package com;

import org.basis.framework.utils.SnowFlakeUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/6/24 10:39 上午
 **/
@MapperScan(basePackages = "com.admin.dao")
@EnableScheduling
@SpringBootApplication
public class Application {
    private Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SnowFlakeUtils.init(SnowFlakeUtils.getDataCenterId(),SnowFlakeUtils.getWorkId());
        SpringApplication.run(Application.class, args);
    }
}
