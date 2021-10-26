package com.common.config.bean;

import com.common.util.SpringContextUtil;
import org.basis.framework.message.bean.MessageAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/10/26 2:40 下午
 **/
@Configuration
public class BeasBeanConfiguration {

    /**
     * 邮箱账号bean
     * @return
     */
    @Bean
    public MessageAccount messageAccount(){
        return MessageAccount.builder()
                .password(SpringContextUtil.property("spring.mail.username"))
                .account(SpringContextUtil.property("spring.mail.password")).build();
    }
}
