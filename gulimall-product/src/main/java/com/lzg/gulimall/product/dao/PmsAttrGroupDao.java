package com.lzg.gulimall.product.dao;

import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzg.gulimall.product.vo.AttrGroupAttrVo;
import com.lzg.gulimall.product.vo.skuItemvo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 属性分组
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:27
 */
@Mapper
public interface PmsAttrGroupDao extends BaseMapper<AttrGroupEntity> {

    List<AttrGroupAttrVo> seletByCateLogId(Long catelogId);

    List<SpuItemAttrGroupVo> getSpuItemAttrGroupByCateIdAndSpuId(Long categoryId, Long spuId);

}
