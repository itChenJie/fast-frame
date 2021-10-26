package com.executor;

import com.executor.constant.RuleFlag;
import com.executor.vo.RuleDto;
import org.basis.framework.error.RRException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/9 5:47 下午
 **/
@Component
public class ExecuteManager implements BeanPostProcessor {
    private final Map<RuleFlag,RuleExecutor> executorIndex = new HashMap<>(RuleFlag.values().length);

    public void computeRule(List<RuleDto> ruleDtos){
        for (RuleDto ruleDto : ruleDtos) {
            switch (ruleDto.getRuleFlag()){
                case LIJIAN:
                    executorIndex.get(ruleDto.getRuleFlag()).executor(ruleDtos);
                case ZHEKOU:
                    executorIndex.get(ruleDto.getRuleFlag()).executor(ruleDtos);
                case MANJIAN:
                    executorIndex.get(ruleDto.getRuleFlag()).executor(ruleDtos);
                default:
                    throw new RRException("Not currently supported, this kind of rule ！"+ruleDto.getRuleFlag());
            }
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof RuleExecutor)){
           return bean;
        }
        RuleExecutor executor = (RuleExecutor)bean;
        RuleFlag ruleFlag = executor.ruleFlag();
        if (executorIndex.containsKey(ruleFlag)){
            throw new RRException("已存在！");
        }
        executorIndex.put(ruleFlag,executor);
        return null;
    }
}
