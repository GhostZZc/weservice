package com.lzg.gulimall.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName: MyMetaObjecthandler
 * @Description: 元数据填充
 * @author: lzg
 * @date: 2023/3/7 15:07
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {


        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
    }

    /**
     * 更新操作，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {


        long id = Thread.currentThread().getId();


        metaObject.setValue("updateTime",LocalDateTime.now());

    }



//    private Long getCurrentUsername(){
//        SecurityContext context = SecurityContextHolder.getContext();
//        LoginPrincipal principal = (LoginPrincipal) context.getAuthentication().getPrincipal();
//        return principal.getId();
//    }
}
