package com.common.config.bean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.common.log.FileLoggerEventHandler;
import com.common.log.LoggerEventHandler;
import com.common.util.SpringContextUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.basis.framework.log.LoggerDisruptorQueue;
import org.basis.framework.log.ProcessLogAppender;
import org.basis.framework.message.bean.MessageAccount;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/10/26 2:40 下午
 **/
@Configuration
public class BeasBeanConfiguration {
    @Autowired
    private BeasProperties beasProperties;
    /**
     * 邮箱账号bean
     * @return
     */
    @Bean
    public MessageAccount messageAccount(){
        BeasProperties.MailConfig mailConfig = beasProperties.getMail();
        return MessageAccount.builder()
                .password(mailConfig.getPassword())
                .account(mailConfig.getUsername())
                .build();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        BeasProperties.MailConfig mailConfig = beasProperties.getMail();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getUsername());
        return mailSender;
    }

    /**
     * 生成验证码bean
     * @return
     */
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 指定Springboot 行转JSON的时候使用的json工具类
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 配置:把空的值的key也返回
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        // 字段如果为null,输出为false,而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullBooleanAsFalse);
        // 数值字段如果为null,输出为0,而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero);
        // List字段如果为null,输出为[],而非null;
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty);
        // 字符类型字段如果为null,输出为"",而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     * 日志监听队列
     * @return
     */
    @Bean
    public LoggerDisruptorQueue loggerDisruptorQueue(){
        LoggerEventHandler loggerEventHandler = SpringContextUtil.createBean(LoggerEventHandler.class, SpringContextUtil.AutoType.AUTOWIRE_BY_TYPE);
        FileLoggerEventHandler fileLoggerEventHandler = SpringContextUtil.createBean(FileLoggerEventHandler.class, SpringContextUtil.AutoType.AUTOWIRE_BY_TYPE);
        return new LoggerDisruptorQueue(loggerEventHandler,fileLoggerEventHandler);
    }

    /**
     * 控制台日志处理bean
     * @return
     */
    @Bean
    public ProcessLogAppender processLogAppender(){
        ProcessLogAppender logAppender = new ProcessLogAppender();
        logAppender.init("CONSOLE");
        return logAppender;
    }

    /**
     * Redisson Client
     * @return
     */
    @Bean
    public RedissonClient redissonClient(){
        BeasProperties.RedissonConfig redissonConfig = beasProperties.getRedis();
        org.redisson.config.Config config = new org.redisson.config.Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s",redissonConfig.getHost(),redissonConfig.getPort()))
                .setPassword(redissonConfig.getPassword());
        return Redisson.create(config);
    }
}
