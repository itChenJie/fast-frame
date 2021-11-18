package com.common.aspect;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.ResponseFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
        //获取对应方法
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //判断代理对象本身是否是连接点所在的目标对象，不是的话就要通过反射重新获取真实方法
        if (joinPoint.getThis().getClass() != joinPoint.getTarget().getClass()) {
            m = ReflectUtil.getMethod(joinPoint.getTarget().getClass(), m.getName(), m.getParameterTypes());
        }
        //通过真实方法获取该方法的参数名称
        LocalVariableTableParameterNameDiscoverer paramNames = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = paramNames.getParameterNames(m);
        Object[] args = joinPoint.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (parameterNames!=null){
            for (int i = 0; i < parameterNames.length; i++) {
                //如果使用requestParam接受参数，加了require=false，这里会存现不存在的现象 TODO
                if (ObjectUtils.isEmpty(args[i])
                        || (args[i] instanceof HttpServletResponse)
                        || (args[i] instanceof HttpServletRequest)){
                    continue;
                }
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
        logger.info("args-json : " + params);
    }

}
