package com.common.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * Spring 环境 工具类
 * @Author ChenWenJie
 * @Data 2021/9/25 4:05 下午
 **/
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("SpringContextUtil：",applicationContext);
        if (ObjectUtil.isNull(SpringContextUtil.applicationContext)) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name beanId
     * @return Object 一个以所给名字注册的bean的实例
     */
    public static synchronized <T> T getBean(String name) {
        try {
            return (T) applicationContext.getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            log.error("BeanName:" + name + "没有找到!", e);
            return null;
        } catch (BeansException e) {
            log.error("BeanName:" + name + "没有找到!", e);
            throw e;
        }
    }

    public enum AutoType {

        AUTOWIRE_NO(0), AUTOWIRE_BY_NAME(1), AUTOWIRE_BY_TYPE(2), AUTOWIRE_CONSTRUCTOR(3), AUTOWIRE_AUTODETECT(4);

        private int value;

        AutoType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }

    /**
     * spring 创建该Bean
     *
     * @param <T>       泛型
     * @param beanClass 泛型 class
     * @param autoType  自动注入方式
     * @return T 对象
     * @see AutoType
     */
    @SuppressWarnings("unchecked")
    public static synchronized <T> T createBean(Class<T> beanClass, AutoType autoType) {
        return (T) applicationContext.getAutowireCapableBeanFactory().createBean(beanClass, autoType.getValue(), false);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取配置文件中的配置属性
     * @param property
     * @return
     */
    public static String property(String property){
        return applicationContext.getEnvironment().getProperty(property);
    }
}
