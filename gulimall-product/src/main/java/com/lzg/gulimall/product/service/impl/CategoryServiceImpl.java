package com.lzg.gulimall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzg.gulimall.product.enums.RedisKey;
import com.lzg.gulimall.product.vo.CategoryVo;
import com.lzg.gulimall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsCategoryDao;
import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.service.ICategoryService;
import org.springframework.util.StringUtils;


@Service("pmsCategoryService")
public class CategoryServiceImpl extends ServiceImpl<PmsCategoryDao, CategoryEntity> implements ICategoryService {

    @Autowired
    private PmsCategoryDao categoryDao;
    @Autowired(required = false)
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public boolean removeByIds(List<Long> catIds) {
        baseMapper.deleteBatchIds(catIds);
        return true;
    }

    @Override
    @Cacheable(cacheNames = {"category"},key ="#root.method.name",sync =true)
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = this.list(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryVo> getCategoryTree() {
        String treeString = redisTemplate.opsForValue().get(RedisKey.CATEGORY_KEY_TREE.getKey());
        if(StringUtils.hasText(treeString)){
            return JSON.parseObject(treeString,new TypeReference<List<CategoryVo>>(){});
        }else{
            List<CategoryVo> categoryVos = BuildCategoryTree();
            String tar = JSON.toJSONString(categoryVos);
            redisTemplate.opsForValue().set(RedisKey.CATEGORY_KEY_TREE.getKey(),tar,1, TimeUnit.DAYS);
            return categoryVos;
        }

    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatelogJson() {
        /**
         * 优化:将数据库中的多次查询变为一次,存至缓存selectList,需要的数据从list取出,避免频繁的数据库交互
         */
        String mapString = redisTemplate.opsForValue().get(RedisKey.CATEGORY_KEY_MAP.getKey());
        if(StringUtils.hasText(mapString)){
            return JSON.parseObject(mapString,new TypeReference<Map<String, List<Catelog2Vo>>>(){});
        }else{
            return getCategoryMap();
        }
    }


    private Map<String,List<Catelog2Vo>> getCategoryMap(){
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        //1.查出所有1级分类
        List<CategoryEntity> level1 = getParent_cid(selectList, 0L);
        //2.封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
                    //1.查出1级分类中所有2级分类
                    List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
                    //2.封装上面的结果
                    List<Catelog2Vo> catelog2Vos = null;
                    if (categoryEntities != null) {
                        catelog2Vos = categoryEntities.stream().map(l2 -> {
                            Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                            //查询当前2级分类的3级分类
                            List<CategoryEntity> level3 = getParent_cid(selectList, l2.getCatId());
                            if (level3 != null) {
                                List<Catelog2Vo.Catelog3Vo> collect = level3.stream().map(l3 -> {
                                    //封装指定格式
                                    Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                                    return catelog3Vo;
                                }).collect(Collectors.toList());
                                catelog2Vo.setCatalog3List(collect);
                            }
                            return catelog2Vo;
                        }).collect(Collectors.toList());
                    }
                    return catelog2Vos;
                }
        ));
        return parent_cid;
    }

    /**
     * 获取上级分类的集合
     * @param selectList
     * @param parent_cid
     * @return
     */
    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parent_cid).collect(Collectors.toList());
        return collect;
    }

    /**
     * 构建树
     * @return
     */
    private List<CategoryVo> BuildCategoryTree(){
        List<CategoryVo> list = categoryDao.list();
        List<CategoryVo> tree = list.stream()
                .filter(it -> it.getParentCid().equals(0L))
                .map(it -> {
                    it.setChildren(getChildren(it, list));
                    return it;
                }).sorted(Comparator.comparingInt(menu -> menu.getSort() == null ? 0 : menu.getSort()))
                .collect(Collectors.toList());
        return tree;
    }

    /**
     * 获取下级集合
     * @param categoryVo
     * @param all
     * @return
     */
    private List<CategoryVo> getChildren(CategoryVo categoryVo, List<CategoryVo> all) {
        return all.stream().filter(it -> it.getParentCid().equals(categoryVo.getCatId()))
                .map(it -> {
                    it.setChildren(getChildren(it, all));
                    return it;
                }).sorted(Comparator.comparingInt(menu -> menu.getSort() == null ? 0 : menu.getSort()))
                .collect(Collectors.toList());
    }


}
