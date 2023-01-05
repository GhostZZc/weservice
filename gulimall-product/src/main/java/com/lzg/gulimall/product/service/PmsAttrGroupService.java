package com.lzg.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.vo.AttrGroupVo;

import java.util.Map;

/**
 * 属性分组
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:27
 */
public interface PmsAttrGroupService extends IService<AttrGroupEntity> {

    AttrGroupVo getById(Long attrGroupId);

    PageUtils queryPage(Map<String, Object> params,Long categoryId);
}

