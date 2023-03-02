package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.AttrEntity;
import com.lzg.gulimall.product.utils.AttrType;
import com.lzg.gulimall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
public interface IAttrService extends IService<AttrEntity> {

    AttrVo save(AttrVo attrVo);

    AttrVo update(AttrVo attrVo);

    AttrVo getById(Long attrId);

    PageUtils queryPage(Map<String, Object> params, Long catelogId, AttrType attrType);


}

