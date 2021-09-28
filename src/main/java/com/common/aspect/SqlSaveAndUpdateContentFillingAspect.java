package com.common.aspect;

import com.common.base.BaseUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.basis.framework.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 *  新增、更新sql 切面注入创建人、更新人、创建时间、更新时间
 * @Author ChenWenJie
 * @Data 2021/9/26 4:42 下午
 **/
@Aspect
@Component
public class SqlSaveAndUpdateContentFillingAspect {
    @Pointcut("execution(* com.baomidou.mybatisplus.core.mapper.BaseMapper.insert(..))")
    private void savePointCut(){}

    @Pointcut("execution(* com.baomidou.mybatisplus.core.mapper.BaseMapper.update*(..))")
    private void updatePointCut(){}

    @Before("savePointCut()")
    public void doBefore(JoinPoint joinPoint){
        filling(joinPoint);
    }

    @Before("updatePointCut()")
    public void doUpdateBefore(JoinPoint joinPoint){
        filling(joinPoint);
    }

    private void filling(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args!=null){
            for (Object arg : args) {
                if (arg instanceof BaseEntity) {
                    BaseEntity baseEntity = (BaseEntity) arg;
                    baseEntity.setCreateUserId(BaseUtil.getUserId());
                    baseEntity.setCreateTime(new Date());
                }
            }
        }
    }
}