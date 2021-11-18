package com.common.config.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Description
 *  枚举类型字段序列化
 * @Author ChenWenJie
 * @Data 2021/11/5 4:27 下午
 **/
public class EnumSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object instanceof Enum) {
            Enum anEnum = (Enum) object;
            out.writeInt(anEnum.ordinal());
            return;
        }

    }
}
