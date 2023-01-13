package com.lzg.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzg.gulimall.common.utils.OrikaBeanMapper;
import com.lzg.gulimall.product.dao.PmsAttrAttrgroupRelationDao;
import com.lzg.gulimall.product.dao.PmsAttrGroupDao;
import com.lzg.gulimall.product.dao.PmsCategoryDao;
import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.lzg.gulimall.product.exception.ProductException;
import com.lzg.gulimall.product.service.PmsAttrAttrgroupRelationService;
import com.lzg.gulimall.product.utils.AttrType;
import com.lzg.gulimall.product.utils.CategoryPathUtil;
import com.lzg.gulimall.product.vo.AttrVo;
import com.lzg.gulimall.product.vo.CategoryVo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsAttrDao;
import com.lzg.gulimall.product.entity.PmsAttrEntity;
import com.lzg.gulimall.product.service.PmsAttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("pmsAttrService")
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrDao, PmsAttrEntity> implements PmsAttrService {

    @Autowired
    private PmsCategoryDao categoryDao;

    @Autowired
    private PmsAttrAttrgroupRelationDao attrGroupDao;

    @Autowired
    private PmsAttrAttrgroupRelationService attrgroupRelationService;


    @Override
    @Transactional
    public AttrVo save(AttrVo attrVo) {
        PmsAttrEntity attr = new PmsAttrEntity();
        OrikaBeanMapper.copy(attrVo, attr);
        boolean save = this.save(attr);
        if (!save) {
            throw new ProductException("属性保存失败");
        }
        PmsAttrAttrgroupRelationEntity relation = new PmsAttrAttrgroupRelationEntity();
        relation.setAttrId(attrVo.getAttrId()).setAttrGroupId(attrVo.getAttrGroupId());
        int insert = attrGroupDao.insert(relation);
        if (insert<1){
            throw new ProductException("属性保存失败");
        }

        return attrVo;
    }

    @Override
    public AttrVo update(AttrVo attrVo) {
        PmsAttrEntity attr = new PmsAttrEntity();
        OrikaBeanMapper.copy(attrVo,attr);
        boolean update = this.updateById(attr);
        if (!update){
            throw new ProductException("属性修改失败");
        }
        PmsAttrAttrgroupRelationEntity relation = new PmsAttrAttrgroupRelationEntity();
        OrikaBeanMapper.copy(attrVo,relation);
        if (relation.getAttrGroupId()!=null){
            LambdaUpdateWrapper<PmsAttrAttrgroupRelationEntity> qw = new LambdaUpdateWrapper<>();
            qw.set(PmsAttrAttrgroupRelationEntity::getAttrGroupId,relation.getAttrGroupId()).eq(PmsAttrAttrgroupRelationEntity::getAttrId,relation.getAttrId());
            boolean update1 = attrgroupRelationService.update(qw);
            if(!update1){
                throw new ProductException("属性修改失败");
            }
        }

        return attrVo;
    }

    @Override
    public AttrVo getById(Long attrId) {
        PmsAttrEntity attr = this.getOne(new QueryWrapper<PmsAttrEntity>().eq("attr_id", attrId));
        List<CategoryVo> list = categoryDao.list();
        AttrVo attrVo = new AttrVo();
        OrikaBeanMapper.copy(attr, attrVo);
        if (attr.getAttrType().equals(1)) {
            PmsAttrAttrgroupRelationEntity attrgroupRelation = attrGroupDao.selectOne(new QueryWrapper<PmsAttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            attrVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
        }
        List<Long> catePath = CategoryPathUtil.getCatePath(list, attr.getCatelogId());
        attrVo.setCatelogPath(catePath);
        return attrVo;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId, AttrType attrType) {
        String key = params.get("key") + "";
        IPage<PmsAttrEntity> page = this.page(
                new Query<PmsAttrEntity>().getPage(params),
                new QueryWrapper<PmsAttrEntity>().eq(!catelogId.equals(0L), "catelog_id", catelogId).eq("attr_type", attrType.getType()).and(Objects.nonNull(params.get("key")), qw -> qw.like("attr_name", key))
        );
        List<CategoryVo> list = categoryDao.list();
        List<AttrVo> tarList = new ArrayList<>();
        List<PmsAttrEntity> records = page.getRecords();
        //设置所属分类
        for (PmsAttrEntity record : records) {
            AttrVo attrVo = new AttrVo();
            OrikaBeanMapper.copy(record, attrVo);
            List<Long> catePath = CategoryPathUtil.getCatePath(list, attrVo.getCatelogId());
            List<CategoryEntity> categorys = categoryDao.selectBatchIds(catePath);
            StringBuilder stringBuilder = new StringBuilder();
            for (CategoryEntity category : categorys) {
                stringBuilder.append(category.getName()).append("/");
            }
            String cateName = stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("/")).toString();
            attrVo.setCatelogName(cateName);
            tarList.add(attrVo);
        }
        IPage<AttrVo> tarPage = new Page<>();
        BeanUtils.copyProperties(page, tarPage, "records");
        tarPage.setRecords(tarList);
        return new PageUtils(tarPage);
    }

}
