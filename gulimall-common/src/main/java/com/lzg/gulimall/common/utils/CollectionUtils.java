package com.lzg.gulimall.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;


import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @ClassName CollectionUtils
 * @Description 集合操作工具
 * @Author 张亚峰
 * @Date 2022/5/13 下午5:16
 * @Version 1.0
 **/
public class CollectionUtils {

    /**
     * @Description TODO
     * @Author 张亚峰
     * @Param object
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     * @Date 2022/5/13 下午5:21
     * @Version 1.0
     **/
    public static final Map<String, Object> objToMap(final Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        String json = JsonMapper.obj2String(object);
        if (!Utils.hasText(json)) {
            return null;
        }
        return JsonMapper.json2GenericObject(json, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * @Description 验证集合是否为空
     * @Author 张亚峰
     * @Param list
     * @Param consumer
     * @Return boolean
     * @Date 2022/5/13 下午5:24
     * @Version 1.0
     **/
    public static final <T extends Collection> boolean ListIsNotEmpty(final T list, Consumer<? super T> consumer) {

        boolean result = Optional.ofNullable(list).map(Collection::stream)
                .orElse(Stream.empty())
                .count() > 0;
        if (result) {
            Optional.ofNullable(consumer).ifPresent(it -> {
                consumer.accept(list);
            });
        }
        return result;
    }

    /**
     * @Description 验证集合不为空
     * @Author 张亚峰
     * @Param list
     * @Return boolean
     * @Date 2022/5/13 下午5:26
     * @Version 1.0
     **/
    public static final <T extends Collection> boolean ListIsNotEmpty(T list) {
        return ListIsNotEmpty(list, null);
    }

    /**
     * @Description 验证Map不为空
     * @Author 张亚峰
     * @Param map
     * @Param consumer
     * @Return boolean
     * @Date 2022/5/13 下午5:26
     * @Version 1.0
     **/
    public static final <T extends Map> boolean MapIsNotEmpty(T map, Consumer<? super T> consumer) {
        if (Objects.isNull(map) || map.size() <= 0) {
            return false;
        }
        Optional.ofNullable(consumer).ifPresent(it -> {
            consumer.accept(map);
        });
        return true;
    }

    /**
     * @Description 验证Map不为空
     * @Author 张亚峰
     * @Param map
     * @Return boolean
     * @Date 2022/5/13 下午5:26
     * @Version 1.0
     **/
    public static final <T extends Map> boolean MapIsNotEmpty(T map) {
        return MapIsNotEmpty(map, null);

    }
}
