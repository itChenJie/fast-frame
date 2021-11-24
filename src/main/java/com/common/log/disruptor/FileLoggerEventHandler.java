package com.common.log.disruptor;

import com.lmax.disruptor.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 *@Description
 *   文件日志事件处理器
 *@Author ChenWenJie
 *@Data 2021/11/24 5:05 下午
 **/
@Component
public class FileLoggerEventHandler implements EventHandler<FileLoggerEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onEvent(FileLoggerEvent fileLoggerEvent, long l, boolean b) {
        messagingTemplate.convertAndSend("/topic/pullFileLogger",fileLoggerEvent.getLog());
    }
}
