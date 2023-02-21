package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.product.dao.PmsAttrAttrgroupRelationDao;
import com.lzg.gulimall.product.dao.PmsAttrDao;
import com.lzg.gulimall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.lzg.gulimall.product.entity.PmsAttrEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsBrandDao;
import com.lzg.gulimall.product.entity.BrandEntity;
import com.lzg.gulimall.product.service.PmsBrandService;


@Service("pmsBrandService")
@AllArgsConstructor
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandDao, BrandEntity> implements PmsBrandService {



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>().like("name",params.get("key"))
        );
        page.setTotal(page.getRecords().size());
        return new PageUtils(page);
    }



}
