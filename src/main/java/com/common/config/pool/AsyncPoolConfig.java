package com.common.config.pool;


import com.alibaba.fastjson.JSON;
import com.common.message.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.basis.framework.message.bean.MessageAccount;
import org.basis.framework.message.bean.MessageDetailsInfo;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description
 *  异步池配置
 * @Author ChenWenJie
 * @Data 2021/7/8 11:07 上午
 **/
@Slf4j
@EnableAsync
@Configuration
public class AsyncPoolConfig implements AsyncConfigurer {
    @Autowired
    private MailMessage message;
    @Autowired
    private MessageAccount messageAccount;
    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        CPU密集型：corePoolSize = CPU核数 + 1
//        IO密集型：corePoolSize = CPU核数 * 2
//        Runtime.getRuntime().availableProcessors() 获取核心线程数
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors()+1);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("FastAsync_");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new AsyncExceptionHandler();
    }

    class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            throwable.printStackTrace();
            log.error("AsyncError:{},Method:{},Param:{}", throwable.getMessage(),method.getName(),
                    JSON.toJSONString(objects));
            MessageDetailsInfo detailsInfo = MessageDetailsInfo.builder()
                    .subject("AsyncError").body(throwable.getMessage())
                    .time(new Date()).receiveAccount(messageAccount).build();
            message.sendMessage(messageAccount,detailsInfo);
        }
    }
}
