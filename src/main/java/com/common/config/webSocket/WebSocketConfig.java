package com.common.config.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @Description
 *  配置WebSocket消息代理端点，即stomp服务端
 *  为了连接安全，setAllowedOrigins设置的允许连接的源地址
 *  如果在非这个配置的地址下发起连接会报403
 *  进一步还可以使用addInterceptors设置拦截器，来做相关的鉴权操作
 * @Author ChenWenJie
 * @Data 2021/9/27 3:18 下午
 **/

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 将clientMessage注册为STOMP的一个端点
        // 客户端在订阅或发布消息到目的路径前，要连接该端点
        // setAllowedOrigins允许所有域连接，否则浏览器可能报403错误
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("http://localhost:63342")
                .withSockJS();
    }


}
