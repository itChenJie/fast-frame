package com.common.config.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
@Data
public class BeasProperties {
    private MailConfig mail;

    private RedissonConfig redis;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MailConfig {
        private String host;

        private int port;

        private String username;

        private String password;

        private String defaultEncoding;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class RedissonConfig {
        private String host;

        private String port;

        private String password;
    }
}
