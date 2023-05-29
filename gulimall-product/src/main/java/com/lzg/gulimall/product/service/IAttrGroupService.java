package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.entity.AttrEntity;
import com.lzg.gulimall.product.vo.AttrGroupAttrVo;
import com.lzg.gulimall.product.vo.AttrGroupVo;
import com.lzg.gulimall.product.vo.skuItemvo.SpuItemAttrGroupVo;


import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:27
 */
public interface IAttrGroupService extends IService<AttrGroupEntity> {

    AttrGroupVo getById(Long attrGroupId);

    PageUtils queryPage(Map<String, Object> params,Long categoryId);

    List<AttrGroupAttrVo> getByCatelogId(Long catelogId);

    List<AttrEntity> getAttrByAttrGroupId(Long attrGroupId);

    PageUtils getNoAttrs(Map<String, Object> params,Long attrgroupId);

    List<SpuItemAttrGroupVo> getSpuItemAttrGroupByCateIdAndSpuId(Long categoryId,Long spuId);
}

