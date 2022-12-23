package com.lzg.gulimall.common.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 * @ClassName OrikaBeanMapper
 * @Description 用于对象属性拷贝
 * @Author lzg
 * @Date 2022/12/22
 * @Version 1.0
 **/
public class OrikaBeanMapper {
    private static final MapperFacade mapperFacade;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder()
                .useAutoMapping(true)
                .mapNulls(true)
                .build();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public static <S, D> void copy(S from, D to) {
        mapperFacade.map(from, to);
    }

    public static <S, D> D copy(S from, Class<D> clazz) {
        return mapperFacade.map(from, clazz);
    }

    public static MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public static <S, D> List<D> copyAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFacade.mapAsList(source, destinationClass);
    }
}
