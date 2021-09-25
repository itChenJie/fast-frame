package com.common.config;

import com.common.interceptor.InjectTokenUserInterceptor;
import com.common.util.SpringContextUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * Web Mvc 配置
 * @Author ChenWenJie
 * @Data 2021/8/6 5:52 下午
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(SpringContextUtil.getBean(InjectTokenUserInterceptor.class));
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
//        ir.excludePathPatterns("**/swagger-ui.html");
        ir.excludePathPatterns("**/doc.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
