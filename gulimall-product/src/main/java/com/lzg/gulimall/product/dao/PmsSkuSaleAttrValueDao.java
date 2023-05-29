package com.lzg.gulimall.product.dao;

import com.lzg.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzg.gulimall.product.vo.skuItemvo.SkuItemSaleAttrsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@Mapper
public interface PmsSkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {




    List<SkuItemSaleAttrsVo> getSaleAttrs(Long spuId);
}
