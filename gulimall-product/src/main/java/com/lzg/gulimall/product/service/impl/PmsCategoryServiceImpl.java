package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.product.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsCategoryDao;
import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.service.PmsCategoryService;


@Service("pmsCategoryService")
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryDao, CategoryEntity> implements PmsCategoryService {

    @Autowired
    private PmsCategoryDao categoryDao;

    @Override
    public boolean removeByIds(List<Long> catIds) {
        baseMapper.deleteBatchIds(catIds);
        return true;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryVo> getCategoryTree() {
        List<CategoryVo> list = categoryDao.list();
        List<CategoryVo> tree = list.stream()
                .filter(it -> it.getParentCid().equals(0L))
                .map(it -> {
                    it.setChildren(getChildren(it, list));
                    return it;
                }).sorted(Comparator.comparingInt(menu -> menu.getSort() == null ? 0 : menu.getSort()))
                .collect(Collectors.toList());
        return tree;
    }

    private List<CategoryVo> getChildren(CategoryVo categoryVo, List<CategoryVo> all) {
        return all.stream().filter(it -> it.getParentCid().equals(categoryVo.getCatId()))
                .map(it -> {
                    it.setChildren(getChildren(it, all));
                    return it;
                }).sorted(Comparator.comparingInt(menu -> menu.getSort() == null ? 0 : menu.getSort()))
                .collect(Collectors.toList());
    }


}