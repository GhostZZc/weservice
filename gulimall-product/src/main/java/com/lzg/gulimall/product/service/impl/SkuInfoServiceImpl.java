package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.product.vo.skuItemvo.SkuItemVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsSkuInfoDao;
import com.lzg.gulimall.product.entity.SkuInfoEntity;
import com.lzg.gulimall.product.service.ISkuInfoService;


@Service("pmsSkuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoDao, SkuInfoEntity> implements ISkuInfoService {

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
        return null;
    }

}
