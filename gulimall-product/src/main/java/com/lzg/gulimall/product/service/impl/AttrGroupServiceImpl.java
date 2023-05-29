package com.lzg.gulimall.product.service.impl;


import com.lzg.gulimall.common.utils.OrikaBeanMapper;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;
import com.lzg.gulimall.product.dao.PmsAttrAttrgroupRelationDao;
import com.lzg.gulimall.product.dao.PmsAttrDao;
import com.lzg.gulimall.product.dao.PmsCategoryDao;
import com.lzg.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.lzg.gulimall.product.entity.AttrEntity;
import com.lzg.gulimall.product.service.IAttrService;
import com.lzg.gulimall.product.utils.CategoryPathUtil;
import com.lzg.gulimall.product.vo.AttrGroupAttrVo;
import com.lzg.gulimall.product.vo.AttrGroupVo;
import com.lzg.gulimall.product.vo.CategoryVo;
import com.lzg.gulimall.product.vo.skuItemvo.SpuItemAttrGroupVo;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lzg.gulimall.product.dao.PmsAttrGroupDao;
import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.service.IAttrGroupService;


@Service("pmsAttrGroupService")
@AllArgsConstructor
public class AttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupDao, AttrGroupEntity> implements IAttrGroupService {


    private PmsCategoryDao categoryDao;

    private PmsAttrAttrgroupRelationDao attrgroupRelationDao;
    private PmsAttrDao attrDao;
    private IAttrService attrService;

    @Override
    public AttrGroupVo getById(Long attrGroupId) {
        AttrGroupEntity attr = this
                .getOne(new QueryWrapper<AttrGroupEntity>().eq("attr_group_id", attrGroupId));
        AttrGroupVo attrGroupVo = new AttrGroupVo();
        OrikaBeanMapper.copy(attr, attrGroupVo);
        //去找分类的具体path
        List<CategoryVo> list = categoryDao.list();
        List<Long> catePath = CategoryPathUtil.getCatePath(list, attrGroupVo.getCatelogId());
        attrGroupVo.setCatelogPath(catePath);
        return attrGroupVo;
    }

    @Override
    public List<AttrEntity> getAttrByAttrGroupId(Long attrGroupId) {
        //先从属性分组和属性关系表查到对应关系
        List<AttrAttrgroupRelationEntity> relationEntities = attrgroupRelationDao
                .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId));
        List<Long> attrIds = relationEntities.stream().map(it -> it.getAttrId()).collect(Collectors.toList());
        List<AttrEntity> pmsAttrEntities = attrDao.selectBatchIds(attrIds);

        if (CollectionUtils.isEmpty(pmsAttrEntities)) {
            return new ArrayList<>(0);
        }
        return pmsAttrEntities;
    }

    @Override
    public PageUtils getNoAttrs(Map<String, Object> params, Long attrgroupId) {
        //先查分组的分类
        AttrGroupEntity one = this.getOne(new QueryWrapper<AttrGroupEntity>().eq("attr_group_id", attrgroupId));
        //查这个分类有哪些属性
        if (Objects.nonNull(one)) {
            Long catelogId = one.getCatelogId();
            IPage<AttrEntity> page = attrService.page(
                    new Query<AttrEntity>().getPage(params),
                    new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId)
            );
            List<AttrEntity> records = page.getRecords();
            //过滤掉分组中包含了的属性
            //先查有哪些属性
            List<AttrAttrgroupRelationEntity> relationEntities = attrgroupRelationDao
                    .selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
            if (CollectionUtils.isNotEmpty(relationEntities)) {
                List<Long> ids = relationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
                //从list中剔除这些属性
                //如果从ids为空,即这个分组没有分类中的任何属性,直接把放行

                List<AttrEntity> collect = records.stream().filter(Objects::nonNull).filter(it -> {
                    return !ids.contains(it.getAttrId());
                }).collect(Collectors.toList());
                page.setRecords(collect);
                return new PageUtils(page);

            } else {
                return new PageUtils(page);
            }

        }
        return null;

    }

    @Override
    public List<SpuItemAttrGroupVo> getSpuItemAttrGroupByCateIdAndSpuId(Long categoryId, Long spuId) {
        return baseMapper.getSpuItemAttrGroupByCateIdAndSpuId(categoryId,spuId);
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        String key = params.get("key") + "";
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>().eq(!categoryId.equals(0L), "catelog_id", categoryId).and(Objects.nonNull(params.get("key")), qw -> qw.like("descript", key))
        );

        return new PageUtils(page);
    }

    @Override
    public List<AttrGroupAttrVo> getByCatelogId(Long catelogId) {
        //先分别去找分组和具体属性
        List<AttrGroupAttrVo> attrGroupAttrVos = baseMapper.seletByCateLogId(catelogId);


        return attrGroupAttrVos;
    }

}
