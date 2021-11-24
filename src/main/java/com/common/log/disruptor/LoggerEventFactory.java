package com.common.log.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 *@Description
 *   进程日志事件工厂类
 *@Author ChenWenJie
 *@Data 2021/11/24 5:05 下午
 **/
public class LoggerEventFactory implements EventFactory<LoggerEvent> {
    @Override
    public LoggerEvent newInstance() {
        return new LoggerEvent();
    }
}
