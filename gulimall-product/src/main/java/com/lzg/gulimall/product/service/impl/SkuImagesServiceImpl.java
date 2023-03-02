package com.lzg.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;
import com.lzg.gulimall.product.dao.PmsSkuImagesDao;
import com.lzg.gulimall.product.entity.SkuImagesEntity;
import com.lzg.gulimall.product.service.ISkuImagesService;


@Service("pmsSkuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<PmsSkuImagesDao, SkuImagesEntity> implements ISkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<SkuImagesEntity>()
        );

        return new PageUtils(page);
    }

}
