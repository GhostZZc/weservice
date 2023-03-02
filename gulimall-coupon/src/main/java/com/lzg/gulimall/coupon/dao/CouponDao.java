package com.lzg.gulimall.coupon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzg.gulimall.coupon.entity.CouponEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 *
 * @author AdverseQ
 * @email sunlightcs@gmail.com
 * @date 2020-11-30 05:47:23
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {

}
