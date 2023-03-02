package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.product.dao.PmsBrandDao;
import com.lzg.gulimall.product.dao.PmsCategoryDao;
import com.lzg.gulimall.product.entity.BrandEntity;
import com.lzg.gulimall.product.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsCategoryBrandRelationDao;
import com.lzg.gulimall.product.entity.CategoryBrandRelationEntity;
import com.lzg.gulimall.product.service.ICategoryBrandRelationService;


@Service("pmsCategoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationDao, CategoryBrandRelationEntity> implements ICategoryBrandRelationService {

    @Autowired
    private PmsCategoryDao categoryDao;

    @Autowired
    private PmsBrandDao brandDao;


    @Override
    public void saveCategoryBrandRelation(CategoryBrandRelationEntity entity) {
        CategoryEntity categoryEntity = categoryDao.selectById(entity.getCatelogId());
        BrandEntity brandEntity = brandDao.selectById(entity.getBrandId());
        entity.setBrandName(brandEntity.getName());
        entity.setCatelogName(categoryEntity.getName());
        this.save(entity);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

}
