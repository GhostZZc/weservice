package com.lzg.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 10:03:29
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

