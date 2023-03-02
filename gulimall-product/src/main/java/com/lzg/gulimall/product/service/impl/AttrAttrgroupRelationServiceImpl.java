package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lzg.gulimall.product.dao.PmsAttrAttrgroupRelationDao;
import com.lzg.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.lzg.gulimall.product.service.IAttrAttrgroupRelationService;


@Service
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements IAttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void removeItems(List<AttrAttrgroupRelationEntity> items) {
        for (AttrAttrgroupRelationEntity item : items) {
            if (Objects.nonNull(item)){
                boolean remove = this.remove(new QueryWrapper<AttrAttrgroupRelationEntity>(item));
            }
        }
    }

}
