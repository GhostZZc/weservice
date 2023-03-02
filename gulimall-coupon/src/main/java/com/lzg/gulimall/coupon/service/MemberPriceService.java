package com.lzg.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author AdverseQ
 * @email sunlightcs@gmail.com
 * @date 2020-11-30 05:47:23
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

