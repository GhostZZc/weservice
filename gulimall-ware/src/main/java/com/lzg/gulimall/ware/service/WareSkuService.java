package com.lzg.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.to.SkuHasStockVo;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取商品的库存
     * @param skuIds
     * @return
     */
    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    /**
     * 修改商品库存
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void updateWareStock(Long skuId,Long wareId,Integer skuNum);
}

