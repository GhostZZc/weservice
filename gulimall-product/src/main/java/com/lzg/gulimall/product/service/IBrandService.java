package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
public interface IBrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BrandEntity> getBrandsByIds(List<Long> brandIds);
}

