package com.lzg.gulimall.ware.service.impl;

import com.lzg.gulimall.common.to.SkuHasStockVo;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.ware.entity.SkuInfoEntity;
import com.lzg.gulimall.ware.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.ware.dao.WareSkuDao;
import com.lzg.gulimall.ware.entity.WareSkuEntity;
import com.lzg.gulimall.ware.service.WareSkuService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private ProductFeignClient productFeignClient;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    /**
     * 查询各sku的库存是否有库存
     * @param skuIds
     * @return
     */
    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        if (!CollectionUtils.isEmpty(skuIds)) {
            List<SkuHasStockVo> collect = skuIds.stream().filter(Objects::nonNull)
                    .map(it -> {
                        SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
                        skuHasStockVo.setSkuId(it);
                        Long skuStock = baseMapper.getSkuStock(it);
                        skuHasStockVo.setHasStock(skuStock != null && skuStock > 0);
                        return skuHasStockVo;
                    }).collect(Collectors.toList());
            return collect;
        }
        return new ArrayList<>(0);
    }

    @Override
    public void updateWareStock(Long skuId, Long wareId, Integer skuNum) {
        //先查,有这条库存信息就修改库存  没有就新建
        R result = new R();
        try {
           result = productFeignClient.getSkuInfo(skuId);
        }catch (Exception e){

        }
        SkuInfoEntity skuInfo = new SkuInfoEntity();
        if (result.getCode()==0){
            skuInfo = (SkuInfoEntity) result.get("skuInfo");
        }
        WareSkuEntity wareSkuEntity = new WareSkuEntity();
        wareSkuEntity.setSkuId(skuId);
        wareSkuEntity.setWareId(wareId);
        QueryWrapper<WareSkuEntity> qw = new QueryWrapper<>(wareSkuEntity);
        WareSkuEntity one = this.getOne(qw);
        if(Objects.nonNull(one)){
            baseMapper.updateSkuStock(skuId,wareId,skuNum);
        }else{
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setSkuName(skuInfo.getSkuName());
            this.save(wareSkuEntity);
        }

    }

}
