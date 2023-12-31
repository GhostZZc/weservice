package com.lzg.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author AdverseQ
 * @email sunlightcs@gmail.com
 * @date 2020-11-30 05:47:23
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

