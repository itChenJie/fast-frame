package com.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description
 *  系统日志收集
 * @Author ChenWenJie
 * @Data 2021/7/6 5:05 下午
 **/
@Slf4j
@Aspect
@Component
public class SysLogAspect {
    @Pointcut(value = "@annotation(com.common.annotation.SysLog)")
    private void sysLogPointCut(){}

    @Around("sysLogPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint point, long time) {
        log.info("system log record：{}");
    }
}
