package com.lzg.gulimall.product.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsSpuImagesDao;
import com.lzg.gulimall.product.entity.SpuImagesEntity;
import com.lzg.gulimall.product.service.ISpuImagesService;


@Service("pmsSpuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<PmsSpuImagesDao, SpuImagesEntity> implements ISpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuImages(Long spuId, List<String> images) {
        if(CollectionUtils.isEmpty(images)|| Objects.isNull(spuId)){

        }else{
            images.stream().map(it->{
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setSpuId(spuId);
                spuImagesEntity.setImgUrl(it);
                this.save(spuImagesEntity);
                return null;
            });
        }
    }

}
