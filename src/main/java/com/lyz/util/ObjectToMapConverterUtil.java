package com.lyz.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapConverterUtil {

    public static Map<String, String> toObjectMap(Object object) throws IllegalAccessException {
        if (object == null) {
            return null;
        }

        Map<String, String> resultMap = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 设置为可访问（私有属性也可以访问）
            resultMap.put(field.getName(), field.get(object).toString());
        }
        return resultMap;
    }
}
