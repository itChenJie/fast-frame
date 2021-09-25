package com.executor.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Description 枚举
 * @Author ChenWenJie
 * @Data 2021/7/9 2:26 下午
 */
@Getter
@AllArgsConstructor
public enum RuleFlag {
    MANJIAN("满减",1),
    ZHEKOU("折扣",2),
    LIJIAN("立减",3);
    /**
     * 描述
     */
    private String description;
    /**
     * 编码
     */
    private Integer code;

    public static RuleFlag of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists"));
    }
}
