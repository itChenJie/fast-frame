package com.admin.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Description 菜单枚举
 * @Author ChenWenJie
 * @Data 2021/10/29 2:19 下午
 */
@Getter
@AllArgsConstructor
public enum MenuType implements IEnum<Integer> {
    CATALOG("目录",0),
    MENU("菜单",1),
    BUTTON("按钮",2);
    /**
     * 描述
     */
    private String key;
    /**
     * 编码
     */
    private Integer value;

    public static MenuType of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.value.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists"));
    }

    public static Integer getValueBykey(String key) {
        MenuType[] applicationStateEnums = values();
        for (MenuType itemEnum : applicationStateEnums) {
            if (itemEnum.key.equals(key)) {
                return itemEnum.getValue();
            }
        }
        throw new IllegalArgumentException(key + " not exists");
    }


    public static String getKeyByValue(Integer value) {
        for (MenuType itemEnum : MenuType.values()) {
            if (value.equals(itemEnum.getValue())) {
                return itemEnum.getKey();
            }
        }
        throw new IllegalArgumentException(value + " not exists");
    }


    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}
