package com.executor;

import com.executor.constant.RuleFlag;
import com.executor.vo.RuleDto;

import java.util.List;

/**
 * @Description 接口
 * @Author ChenWenJie
 * @Data 2021/7/9 2:28 下午
 */
public interface RuleExecutor {

    RuleFlag ruleFlag();

    void executor(List<RuleDto> ruleDtos);
}
