package com.common.queue;

import com.common.log.LoggerMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description
 * 作为日志系统输出的日志的一个临时载体
 * @Author ChenWenJie
 * @Data 2021/9/27 3:13 下午
 **/
public class LoggerQueue {
    //队列大小
    public static final int QUEUE_MAX_SIZE = 10000;
    private static LoggerQueue alarmMessageQueue = new LoggerQueue();
    //阻塞队列
    private BlockingQueue blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    private LoggerQueue() {
    }

    public static LoggerQueue getInstance() {
        return alarmMessageQueue;
    }

    public boolean push(LoggerMessage log) {
        //队列满了就抛出异常，不阻塞
        return this.blockingQueue.add(log);
    }

    public LoggerMessage poll() {
        LoggerMessage result = null;
        try {
            result = (LoggerMessage) this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
