package com.lzg.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsProductAttrValueDao;
import com.lzg.gulimall.product.entity.ProductAttrValueEntity;
import com.lzg.gulimall.product.service.IProductAttrValueService;


@Service("pmsProductAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueDao, ProductAttrValueEntity> implements IProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateAttr(Long spuId, List<ProductAttrValueEntity> list) {
        //先删除原有的商品属性
        List<ProductAttrValueEntity> beforeList = this.list(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        List<Long> ids = beforeList.stream().map(ProductAttrValueEntity::getId).collect(Collectors.toList());
        this.removeBatchByIds(ids);
        //再新建
        List<ProductAttrValueEntity> collect = list.stream().map(it -> {
            it.setSpuId(spuId);
            return it;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}
