package com.lzg.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 10:03:29
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

