package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.vo.CategoryVo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
public interface PmsCategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean removeByIds(List<Long> catIds);


    List<CategoryVo> getCategoryTree();
}

