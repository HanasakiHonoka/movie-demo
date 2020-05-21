package com.xzx.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;

public class MapConvertUtil {

    public static MultiValueMap<String, Object> obj2Map(Object obj) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            map.add(field.getName(), field.get(obj));
        }
        return map;
    }
}
