package com.common.config.serializer;

import com.admin.enums.MenuType;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;

import java.lang.reflect.Type;

/**
 * @Description
 * 菜单枚举类型字段反序列化
 * @Author ChenWenJie
 * @Data 2021/11/5 4:31 下午
 **/
public class MenuTypeDeserializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object value = parser.parse();
        return value == null ? null : (T) MenuType.of(TypeUtils.castToInt(value));
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

}
