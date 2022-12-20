package com.lzg.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.ware.entity.TbUserEntity;

import java.util.Map;

/**
 * 用户
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
public interface TbUserService extends IService<TbUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

