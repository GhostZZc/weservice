package com.lzg.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 10:03:29
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

