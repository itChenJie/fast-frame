package com.common.config.serializer;

import com.admin.enums.MenuType;
import com.admin.enums.UserStatusEnum;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * @Description
 *  用户状态枚举类型反序列化
 * @Author ChenWenJie
 * @Data 2021/11/5 4:36 下午
 **/
public class UserStatusDeserializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (parser.getLexer().stringVal()!=null){
            String val = parser.getLexer().stringVal();
            return (T) UserStatusEnum.of(Integer.valueOf(val));
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
