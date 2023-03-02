package com.lzg.gulimall.ware.dao;

import com.lzg.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {


    Long getSkuStock(Long skuId);

}
