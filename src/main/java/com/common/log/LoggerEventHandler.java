package com.common.log;


import com.lmax.disruptor.EventHandler;
import org.basis.framework.log.LoggerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *@Description
 * 进程日志事件处理器
 *@Author ChenWenJie
 *@Data 2021/11/24 5:06 下午
 **/
@Component
public class LoggerEventHandler implements EventHandler<LoggerEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onEvent(LoggerEvent stringEvent, long l, boolean b) {
        messagingTemplate.convertAndSend("/topic/pullLogger",stringEvent.getLog());
    }
}
