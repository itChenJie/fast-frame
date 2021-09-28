package com.common.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.basis.framework.annotation.Permissions;
import org.basis.framework.utils.R;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * 验证拦截器
 * @Author ChenWenJie
 * @Data 2021/9/28 3:28 下午
 **/
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)
                ||(handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler)) {
            return true;
        }
        HandlerMethod h = (HandlerMethod) handler;
        Permissions permissions = h.getMethod().getAnnotation(Permissions.class);
        if(permissions!=null&&permissions.value().length>0){
            //组装应有权限列表
            boolean isRelease=false;
            if (!ObjectUtils.isEmpty(BaseUtil.getUser())&& CollectionUtil.isNotEmpty(BaseUtil.getUser().getAuthoritys())){
                List<String> arr= BaseUtil.getUser().getAuthoritys();
                for (String key : permissions.value()) {
                    if(!isRelease){
                        if(arr.contains(key)){
                            isRelease=true;
                        }
                    }
                }
            }
            if(!isRelease){
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSONObject.toJSONString(R.error("无权访问")));
                return false;
            }
        }
        return true;
    }
}
