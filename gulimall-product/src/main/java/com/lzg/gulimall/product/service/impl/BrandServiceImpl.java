package com.lzg.gulimall.product.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsBrandDao;
import com.lzg.gulimall.product.entity.BrandEntity;
import com.lzg.gulimall.product.service.IBrandService;


@Service("pmsBrandService")
@AllArgsConstructor
public class BrandServiceImpl extends ServiceImpl<PmsBrandDao, BrandEntity> implements IBrandService {



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
