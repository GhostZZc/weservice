package com.lzg.gulimall.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Objects;


public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T string2Obj(String src, Class<T> clazz) {
        if (src == null || clazz == null) {
            return null;
        }
        try {
            return (T) (clazz.getTypeName().equals(String.class) ? src : objectMapper.readValue(src, clazz));
        } catch (Exception e) {
            return null;
        }
    }


    public synchronized static <T> T json2GenericObject(String jsonString, TypeReference<T> tr) {
        if (jsonString != null && !("".equals(jsonString))) {
            try {
                return (T) (tr.getType().equals(String.class) ? jsonString : objectMapper.readValue(jsonString, tr));
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static <T> T mapToObje(Object map,TypeReference<T> tr){

        if (Objects.isNull(map) || Objects.isNull(tr)) {
            return null;
        }

        String _json = obj2String(map);

        return json2GenericObject(_json,tr);


    }
}
