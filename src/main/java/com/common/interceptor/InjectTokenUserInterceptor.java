package com.common.interceptor;

import com.admin.entity.AdminUser;
import com.common.base.BaseUtil;
import com.common.util.SpringContextUtil;
import com.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.basis.framework.annotation.IgnoreSecurity;
import org.basis.framework.error.BizCodeEnume;
import org.basis.framework.error.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description
 *  通过token注入当前登陆人员
 * @Author ChenWenJie
 * @Data 2021/8/6 5:55 下午
 **/
@Slf4j
public class InjectTokenUserInterceptor extends HandlerInterceptorAdapter {
    private RedisUtils redisUtils;
    public InjectTokenUserInterceptor(){
        this.redisUtils = SpringContextUtil.getBean(RedisUtils.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)
                ||(handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = request.getRequestURI();
        if (requestPath.contains("/v2/api-docs") || requestPath.contains("/v3/api-docs") || requestPath.contains("/swagger-resources")) {
            return true;
        }
        if (requestPath.contains("/error")) {
            return true;
        }
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }
        String token = request.getHeader("Admin-Token");
        if (StringUtils.isBlank(token)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new UnauthorizedException("请先登陆！",BizCodeEnume.USER_UN_LOGIN.getCode());
        }
        AdminUser adminUser = redisUtils.get(token, AdminUser.class);
        if (adminUser==null){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new UnauthorizedException("请重新登陆！",BizCodeEnume.USER_UN_LOGIN.getCode());
        }
        BaseUtil.setUser(adminUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseUtil.removeThreadLocal();
    }
}
