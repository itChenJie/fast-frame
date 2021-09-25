package com.common.config.bean;

import org.basis.framework.error.DefinitionExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/9/24 4:42 下午
 **/
@Configuration
public class CommonBeanConfig {

    @Bean()
    public DefinitionExceptionHandler definitionExceptionHandler(){
        return new DefinitionExceptionHandler();
    }
}
