package com.executor.impl;

import com.executor.AbstractExecutor;
import com.executor.RuleExecutor;
import com.executor.constant.RuleFlag;
import com.executor.vo.RuleDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/9 2:38 下午
 **/
@Component
public class ManJianRuleExecutor extends AbstractExecutor implements RuleExecutor {
    @Override
    public RuleFlag ruleFlag() {
        return RuleFlag.MANJIAN;
    }

    @Override
    public void executor(List<RuleDto> ruleDtos) {
        double sum = retain2Decimals(goodsCostSum(ruleDtos));
        if (sum>=20){
            System.out.println(sum-5);
        }
    }
}
