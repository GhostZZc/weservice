package com.lzg.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsSpuInfoDao;
import com.lzg.gulimall.product.entity.PmsSpuInfoEntity;
import com.lzg.gulimall.product.service.PmsSpuInfoService;


@Service("pmsSpuInfoService")
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoDao, PmsSpuInfoEntity> implements PmsSpuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSpuInfoEntity> page = this.page(
                new Query<PmsSpuInfoEntity>().getPage(params),
                new QueryWrapper<PmsSpuInfoEntity>().eq(Objects.nonNull(params.get("brandId")),"brand_id",params.get("brandId"))
                        .eq(Objects.nonNull(params.get("catelogId")),"catelog_id",params.get("catelogId")).like(Objects.nonNull(params.get("key")),"spu_name",params.get("key"))
        );

        return new PageUtils(page);
    }

}
