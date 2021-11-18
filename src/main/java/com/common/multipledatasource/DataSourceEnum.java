package com.common.multipledatasource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Description 枚举
 * @Author ChenWenJie
 * @Data 2021/11/15 3:42 下午
 */
@Getter
public enum DataSourceEnum {
    SHOP("shop", "网上商城数据源");

    /**
     * 描述
     */
    private String dataSource;
    /**
     * 编码
     */
    private String desc;

    DataSourceEnum(String dataSource, String desc) {
        this.dataSource =dataSource;
        this.desc = desc;
    }
}
