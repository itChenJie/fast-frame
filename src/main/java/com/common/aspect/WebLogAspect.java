package com.common.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 *  web端日志统一处理
 * @Author ChenWenJie
 * @Data 2021/9/24 6:22 下午
 **/
@Component
@Aspect
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.*.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        doBefore(joinPoint);
        Object result = joinPoint.proceed();
        // 打印出参
        logger.info("Controller Response : {}",JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        logger.info("request end:-------------------------------");
        return result;
    }

    private void doBefore(JoinPoint joinPoint) throws Throwable{
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //这一步获取到的方法有可能是代理方法也有可能是真实方法
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //判断代理对象本身是否是连接点所在的目标对象，不是的话就要通过反射重新获取真实方法
        if (joinPoint.getThis().getClass() != joinPoint.getTarget().getClass()) {
            m = ReflectUtil.getMethod(joinPoint.getTarget().getClass(), m.getName(), m.getParameterTypes());
        }
        //通过真实方法获取该方法的参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = paramNames.getParameterNames(m);
        //获取连接点方法运行时的入参列表
        Object[] args = joinPoint.getArgs();
        //将参数名称与入参值一一对应起来
        Map<String, Object> params = new HashMap<>();
        //自己写的一个判空类方法
        if (parameterNames!=null){
            for (int i = 0; i < parameterNames.length; i++) {
                //这里加一个判断，如果使用requestParam接受参数，加了require=false，这里会存现不存在的现象
                if (ObjectUtils.isEmpty(args[i])){
                    continue;
                }
                //通过所在类转换，获取值，包含各种封装类都可以
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.convertValue(args[i],args[i].getClass());
                params.put(parameterNames[i],JSON.toJSON(objectMapper.convertValue(args[i],args[i].getClass())));
            }
        }
        logger.info("request start:-------------------------------");
        logger.info("url : " + request.getRequestURL().toString());
        logger.info("http_method : " + request.getMethod());
        logger.info("ip : " + request.getRemoteAddr());
        logger.info("class_method : " + joinPoint.getSignature().getDeclaringTypeName()+ "." + joinPoint.getSignature().getName());
        //这里经过处理，就可以获得参数名字与值一一对应
        logger.info("args-json : " + params);
    }

}
