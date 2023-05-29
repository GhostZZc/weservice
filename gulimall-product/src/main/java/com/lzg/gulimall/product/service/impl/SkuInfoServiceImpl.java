package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.product.entity.SkuImagesEntity;
import com.lzg.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.lzg.gulimall.product.entity.SpuInfoDescEntity;
import com.lzg.gulimall.product.service.*;
import com.lzg.gulimall.product.vo.skuItemvo.SkuItemSaleAttrsVo;
import com.lzg.gulimall.product.vo.skuItemvo.SkuItemVo;
import com.lzg.gulimall.product.vo.skuItemvo.SpuItemAttrGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsSkuInfoDao;
import com.lzg.gulimall.product.entity.SkuInfoEntity;


@Service("pmsSkuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoDao, SkuInfoEntity> implements ISkuInfoService {

    @Autowired
    private ISkuImagesService skuImagesService;
    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private IAttrGroupService attrGroupService;
    @Autowired
    private ISpuInfoDescService spuInfoDescService;
    @Autowired
    private ThreadPoolExecutor executor;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByParams(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        QueryWrapper<SkuInfoEntity> qw = new QueryWrapper<>();
        qw.eq("spu_id",spuId);
        List<SkuInfoEntity> list = this.list(qw);
        return list;
    }

    @Override
    public SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();
        //设置sku基本信息
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            SkuInfoEntity skuInfo = this.getById(skuId);
            skuItemVo.setInfo(skuInfo);
            return skuInfo;
        }, executor);
        //设置sku销售属性
        CompletableFuture<Void> saleFuture = infoFuture.thenAcceptAsync((res) -> {
            List<SkuItemSaleAttrsVo> saleAttrs = skuSaleAttrValueService.getSaleAttrs(res.getSpuId());
            skuItemVo.setSaleAttr(saleAttrs);
        }, executor);
        //规格参数
        CompletableFuture<Void> baseFuture = infoFuture.thenAcceptAsync((res) -> {
            List<SpuItemAttrGroupVo> spuItemAttrGroupByCateIdAndSpuIds =
                    attrGroupService.getSpuItemAttrGroupByCateIdAndSpuId(res.getCatelogId(), res.getSpuId());
            skuItemVo.setGroupAttrs(spuItemAttrGroupByCateIdAndSpuIds);
        }, executor);
        //详细信息
        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((res) -> {
            SpuInfoDescEntity descEntity = spuInfoDescService.getById(res.getSpuId());
            skuItemVo.setDesp(descEntity);;
        }, executor);
        //设置sku照片集合
        CompletableFuture<Void> imagFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> images = skuImagesService.list(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
            skuItemVo.setImagesEntites(images);
        }, executor);

        CompletableFuture.allOf(saleFuture,baseFuture,descFuture,imagFuture).get();


        return skuItemVo;
    }

}
