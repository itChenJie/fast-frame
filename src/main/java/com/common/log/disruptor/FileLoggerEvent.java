package com.common.log.disruptor;

/**
 *@Description
 * 文件日志事件内容载体
 *@Author ChenWenJie
 *@Data 2021/11/24 5:01 下午
 **/
public class FileLoggerEvent {
    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
