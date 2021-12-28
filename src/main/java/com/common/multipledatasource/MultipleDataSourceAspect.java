package com.common.multipledatasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/11/15 2:55 下午
 **/
@Slf4j
@Aspect
@Component
public class MultipleDataSourceAspect {

    @Pointcut("@annotation(MultipleDataSource)||@within(MultipleDataSource)")
    private void pointcut(){}

    @Around("pointcut()")
    public Object beforeSwitchDS(ProceedingJoinPoint point){
        Class<?> className = point.getTarget().getClass();

        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = null;
        try {
            Method method = className.getMethod(methodName, argClass);
            if (className.isAnnotationPresent(MultipleDataSource.class)){
                MultipleDataSource annotation = className.getAnnotation(MultipleDataSource.class);
                dataSource = annotation.value();
            }
            // 方法上的优先级更高于类
            if (method.isAnnotationPresent(MultipleDataSource.class)){
                MultipleDataSource annotation = className.getAnnotation(MultipleDataSource.class);
                dataSource = annotation.value();
            }
        }catch (Exception e){
            log.error("aop get @MultipleDataSource error :{}",e);
        }
        if (StringUtils.isNotEmpty(dataSource)){
            DataSourceContextHolder.setDb(dataSource);
        }
        Object result = null;
        try {
             result = point.proceed();
        }catch (Throwable throwable){
            log.error("MultipleDataSourceAspect find error :",throwable);
        }finally {
            DataSourceContextHolder.clearDB();
        }

        return result;
    }
}
