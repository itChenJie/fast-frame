package com.executor.impl;

import com.executor.AbstractExecutor;
import com.executor.RuleExecutor;
import com.executor.constant.RuleFlag;
import com.executor.vo.RuleDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * 立减规则执行器
 * @Author ChenWenJie
 * @Data 2021/7/9 2:38 下午
 **/
@Component
public class LiJianRuleExecutor extends AbstractExecutor implements RuleExecutor {
    @Override
    public RuleFlag ruleFlag() {
        return RuleFlag.LIJIAN;
    }

    @Override
    public void executor(List<RuleDto> ruleDtos) {
        double goodsCostSum = retain2Decimals(goodsCostSum(ruleDtos));
        System.out.println(goodsCostSum - 10);
    }
}
