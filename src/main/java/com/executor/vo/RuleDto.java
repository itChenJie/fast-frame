package com.executor.vo;

import com.executor.constant.RuleFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/9 2:30 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleDto {
    private Double price;
    private Integer count;
    private RuleFlag ruleFlag;
}
