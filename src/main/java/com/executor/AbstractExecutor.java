package com.executor;

import com.executor.vo.RuleDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/9 2:44 下午
 **/
public abstract class AbstractExecutor {

    /**
     * 商品总价
     * @param ruleDtos
     * @return
     */
    protected double goodsCostSum(List<RuleDto> ruleDtos){
        return ruleDtos.stream().mapToDouble(item ->item.getPrice()*item.getCount()).sum();
    }

    /**
     * 保留两位小数
     * @param value
     * @return
     */
    protected double retain2Decimals(double value){
        return new BigDecimal(value).setScale(2,
                BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
