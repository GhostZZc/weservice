package com.lzg.gulimall.product.dao;

import com.lzg.gulimall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品属性
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@Mapper
public interface PmsAttrDao extends BaseMapper<AttrEntity> {

    List<Long> filterAttrIdsSearchAble(List<Long> attrIds);

}
