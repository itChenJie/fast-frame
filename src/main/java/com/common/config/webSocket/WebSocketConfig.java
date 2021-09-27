package com.common.config.webSocket;


import com.common.log.LoggerMessage;
import com.common.queue.LoggerQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 *  配置WebSocket消息代理端点，即stomp服务端
 *  为了连接安全，setAllowedOrigins设置的允许连接的源地址
 *  如果在非这个配置的地址下发起连接会报403
 *  进一步还可以使用addInterceptors设置拦截器，来做相关的鉴权操作
 * @Author ChenWenJie
 * @Data 2021/9/27 3:18 下午
 **/
@Slf4j
//注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 将clientMessage注册为STOMP的一个端点
        // 客户端在订阅或发布消息到目的路径前，要连接该端点
        // setAllowedOrigins允许所有域连接，否则浏览器可能报403错误
        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 推送日志到/topic/pullLogger
     */
    @PostConstruct
    public void pushLogger(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        LoggerMessage log = LoggerQueue.getInstance().poll();
                        if(log!=null){
                            if(messagingTemplate!=null)
                                messagingTemplate.convertAndSend("/topic/pullLogger",log);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(runnable);
    }

}
