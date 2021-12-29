package com.common.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * feign 全局参数配置
 * @Author ChenWenJie
 * @Data 2021/12/29 12:15 下午
 **/
@Configuration
public class FeignConfigure {

    /**
     * feign 重试bean配置(全局化配置)
     * period：间隔时间
     * maxPeriod ：最大周期(时间)
     * maxAttempts：最大尝试次数
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100,1000,4);
    }
}
