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
import com.lzg.gulimall.product.entity.PmsCategoryBrandRelationEntity;
import com.lzg.gulimall.product.service.PmsCategoryBrandRelationService;


@Service("pmsCategoryBrandRelationService")
public class PmsCategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationDao, PmsCategoryBrandRelationEntity> implements PmsCategoryBrandRelationService {

    @Autowired
    private PmsCategoryDao categoryDao;

    @Autowired
    private PmsBrandDao brandDao;


    @Override
    public void saveCategoryBrandRelation(PmsCategoryBrandRelationEntity entity) {
        CategoryEntity categoryEntity = categoryDao.selectById(entity.getCatelogId());
        BrandEntity brandEntity = brandDao.selectById(entity.getBrandId());
        entity.setBrandName(brandEntity.getName());
        entity.setCatelogName(categoryEntity.getName());
        this.save(entity);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsCategoryBrandRelationEntity> page = this.page(
                new Query<PmsCategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<PmsCategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

}
