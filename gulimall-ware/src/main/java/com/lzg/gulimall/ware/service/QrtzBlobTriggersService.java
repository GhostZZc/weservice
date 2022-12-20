package com.lzg.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.ware.entity.QrtzBlobTriggersEntity;

import java.util.Map;

/**
 * 
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
public interface QrtzBlobTriggersService extends IService<QrtzBlobTriggersEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

