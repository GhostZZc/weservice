package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.common.utils.OrikaBeanMapper;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;
import com.lzg.gulimall.product.dao.PmsCategoryDao;
import com.lzg.gulimall.product.utils.CategoryPathUtil;
import com.lzg.gulimall.product.vo.AttrGroupVo;
import com.lzg.gulimall.product.vo.CategoryVo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lzg.gulimall.product.dao.PmsAttrGroupDao;
import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.service.PmsAttrGroupService;


@Service("pmsAttrGroupService")
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupDao, AttrGroupEntity> implements PmsAttrGroupService {

    @Autowired
    private PmsCategoryDao categoryDao;


    @Override
    public AttrGroupVo getById(Long attrGroupId) {
        AttrGroupEntity attr = this
                .getOne(new QueryWrapper<AttrGroupEntity>().eq("attr_group_id", attrGroupId));
        AttrGroupVo attrGroupVo = new AttrGroupVo();
        OrikaBeanMapper.copy(attr,attrGroupVo);
        //去找分类的具体path
        List<CategoryVo> list = categoryDao.list();
        List<Long> catePath = CategoryPathUtil.getCatePath(list,attrGroupVo.getCatelogId());
        attrGroupVo.setCatelogPath(catePath);
        return attrGroupVo;
    }



    @Override
    public PageUtils queryPage(Map<String, Object> params,Long categoryId) {
        String key = params.get("key")+"";
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>().eq(!categoryId.equals(0L),"catelog_id",categoryId).and(Objects.nonNull( params.get("key")),qw->qw.like("descript",key))
        );

        return new PageUtils(page);
    }

}
