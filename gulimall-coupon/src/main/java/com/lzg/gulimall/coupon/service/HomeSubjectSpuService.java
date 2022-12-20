package com.lzg.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 10:03:29
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

