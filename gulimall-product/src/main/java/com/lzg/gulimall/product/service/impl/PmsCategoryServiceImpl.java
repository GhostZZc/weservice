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
import com.lzg.gulimall.product.entity.PmsCategoryEntity;
import com.lzg.gulimall.product.service.PmsCategoryService;


@Service("pmsCategoryService")
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryDao, PmsCategoryEntity> implements PmsCategoryService {

    @Autowired
    private PmsCategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsCategoryEntity> page = this.page(
                new Query<PmsCategoryEntity>().getPage(params),
                new QueryWrapper<PmsCategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryVo> getCategoryTree() {
        List<CategoryVo> list = categoryDao.list();
        List<CategoryVo> tree = list.stream()
                .filter(it -> it.getParentCid() == 0)
                .map(it -> {
                    it.setChildren(getChildren(it, list));
                    return it;
                }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
        return tree;
    }


    private List<CategoryVo> getChildren(CategoryVo up,List<CategoryVo> list){
        return list.stream()
                .filter(it->it.getParentCid()==up.getCatId())
                .map(it->{
                    it.setChildren(getChildren(it,list));
                    return it;
                }).sorted(Comparator.comparingInt(menu->(menu.getSort())==null?0: menu.getSort()))
                .collect(Collectors.toList());

    }

}