package com.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description 用户状态枚举
 * @Author ChenWenJie
 * @Data 2021/9/26 2:19 下午
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    Disable("禁用",0),
    NORMAL("正常",1),
    INACTIVATED("未激活",2),
    RESIGN("离职",3);
    /**
     * 描述
     */
    @JsonValue
    private String key;
    /**
     * 编码
     */
    @EnumValue
    private Integer value;

    public static UserStatusEnum of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.value.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists"));
    }

    @JsonCreator
    public static UserStatusEnum valueOf(final Integer value) {
        return Optional.ofNullable(value)
                .flatMap(it -> Stream.of(values())
                        .filter(it2 -> it2.value == value)
                        .findFirst()).orElse(null);
    }

    public static Integer getValueBykey(String key){
        UserStatusEnum[] applicationStateEnums = values();
        for (UserStatusEnum itemEnum : applicationStateEnums) {
            if (itemEnum.key.equals(key)) {
                return itemEnum.getValue();
            }
        }
        throw new IllegalArgumentException(key +" not exists");
    }


    public static String getKeyByValue(Integer value){
        for (UserStatusEnum itemEnum : UserStatusEnum.values()) {
            if(value.equals(itemEnum.getValue())){
                return itemEnum.getKey();
            }
        }
        throw new IllegalArgumentException(value +" not exists");
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }


}
