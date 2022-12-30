package com.lzg.gulimall.product.dao;

import com.lzg.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzg.gulimall.product.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品三级分类
 * 
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@Mapper
public interface PmsCategoryDao extends BaseMapper<CategoryEntity> {


    List<CategoryVo> list();

}
