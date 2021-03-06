package com.common.log;

import org.basis.framework.log.LoggerDisruptorQueue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *@Description
 *   文件日志监听
 *@Author ChenWenJie
 *@Data 2021/11/24 5:06 下午
 **/
@Service
public class FileLogListening implements ApplicationContextAware{
    //上次文件大小
    private long lastTimeFileSize = 0;

    @Autowired
    Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String logPath = environment.getProperty("weblogPath");
        try {
            File logFile = ResourceUtils.getFile(logPath);
            final RandomAccessFile randomFile = new RandomAccessFile(logFile, "rw");
            //指定文件可读可写
            ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
            exec.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        randomFile.seek(lastTimeFileSize);
                        String tmp = "";
                        while ((tmp = randomFile.readLine()) != null) {
                            String log=new String(tmp.getBytes("ISO8859-1"));
                            LoggerDisruptorQueue.publishEvent(log);
                        }
                        lastTimeFileSize = randomFile.length();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 监听日志文件
     *
     * @throws IOException
     */
    @PostConstruct
    public void start() throws IOException {

    }
}