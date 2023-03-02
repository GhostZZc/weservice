package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:27
 */
public interface ICategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    void saveCategoryBrandRelation(CategoryBrandRelationEntity entity);

    PageUtils queryPage(Map<String, Object> params);

}

