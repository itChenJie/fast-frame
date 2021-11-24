package com.common.log.disruptor;


import com.common.log.LoggerMessage;

/**
 *@Description
 *   进程日志事件内容载体
 *@Author ChenWenJie
 *@Data 2021/11/24 5:05 下午
 **/
public class LoggerEvent {

    private LoggerMessage log;

    public LoggerMessage getLog() {
        return log;
    }

    public void setLog(LoggerMessage log) {
        this.log = log;
    }
}
