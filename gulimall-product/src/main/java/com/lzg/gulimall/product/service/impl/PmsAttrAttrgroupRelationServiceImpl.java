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
import com.lzg.gulimall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.lzg.gulimall.product.service.PmsAttrAttrgroupRelationService;


@Service
public class PmsAttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationDao, PmsAttrAttrgroupRelationEntity> implements PmsAttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsAttrAttrgroupRelationEntity> page = this.page(
                new Query<PmsAttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<PmsAttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void removeItems(List<PmsAttrAttrgroupRelationEntity> items) {
        for (PmsAttrAttrgroupRelationEntity item : items) {
            if (Objects.nonNull(item)){
                boolean remove = this.remove(new QueryWrapper<PmsAttrAttrgroupRelationEntity>(item));
            }
        }
    }

}
