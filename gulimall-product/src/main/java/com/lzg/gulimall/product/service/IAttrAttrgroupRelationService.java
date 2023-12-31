package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
public interface IAttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void removeItems(List<AttrAttrgroupRelationEntity> items);
}

