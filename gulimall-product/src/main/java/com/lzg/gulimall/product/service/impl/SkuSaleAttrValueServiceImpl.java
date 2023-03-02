package com.lzg.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsSkuSaleAttrValueDao;
import com.lzg.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.lzg.gulimall.product.service.ISkuSaleAttrValueService;


@Service("pmsSkuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements ISkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

}
