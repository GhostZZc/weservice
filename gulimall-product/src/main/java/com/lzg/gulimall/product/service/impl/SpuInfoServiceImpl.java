package com.lzg.gulimall.product.service.impl;

import com.lzg.gulimall.common.to.SkuReductionTo;
import com.lzg.gulimall.common.to.SpuBoundTo;
import com.lzg.gulimall.common.utils.OrikaBeanMapper;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.product.entity.*;
import com.lzg.gulimall.product.exception.ProductException;
import com.lzg.gulimall.product.feign.CouponFeignService;
import com.lzg.gulimall.product.service.*;
import com.lzg.gulimall.product.vo.AttrVo;
import com.lzg.gulimall.product.vo.spvsavevo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.Query;

import com.lzg.gulimall.product.dao.PmsSpuInfoDao;
import org.springframework.util.StringUtils;


@Service("pmsSpuInfoService")
@Slf4j
public class SpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoDao, SpuInfoEntity> implements ISpuInfoService {

    @Autowired
    private ISpuImagesService spuImagesService;
    @Autowired
    private ISpuInfoDescService spuInfoDescService;
    @Autowired
    private IProductAttrValueService productAttrValueService;
    @Autowired
    private ISkuInfoService skuInfoService;
    @Autowired
    private ISkuImagesService skuImagesService;
    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private IAttrService attrService;
    @Autowired
    private CouponFeignService couponFeignService;


    @Override

    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            //等价sql: status=1 and (id=1 or spu_name like xxx)
            queryWrapper.and((w) -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("publish_status", status);
        }
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    /**
     * 保存Spu
     *
     * @param spuSaveVo
     */
    @Override
    public void saveSpuVo(SpuSaveVo spuSaveVo) {
        //1.先保存spu基本信息pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        OrikaBeanMapper.copy(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date())
                .setUpdateTime(new Date());
        this.save(spuInfoEntity);
        Long spuId = spuInfoEntity.getId();
        //2.保存spuImages  pms_spu_images
        List<String> images = spuSaveVo.getImages();
        spuImagesService.saveSpuImages(spuId, images);
        //3.保存spu decript pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        String decriptAll = String.join(",", decript);
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuId);
        spuInfoDescEntity.setDecript(decriptAll);
        spuInfoDescService.save(spuInfoDescEntity);
        //4.保存spu attr_value  pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        baseAttrs.stream().map(attr -> {
            Long attrId = attr.getAttrId();
            AttrVo attrDetil = attrService.getById(attrId);
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            OrikaBeanMapper.copy(attr, productAttrValueEntity);
            productAttrValueEntity.setSpuId(spuId);
            productAttrValueEntity.setAttrName(attrDetil.getAttrName());
            productAttrValueService.save(productAttrValueEntity);
            return null;
        });
        //5.保存此spu积分信息;(跨服务)
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        OrikaBeanMapper.copy(bounds, spuBoundTo);
        spuBoundTo.setSpuId(spuId);
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if (r.getCode() != 0) {
            log.error("保存商品积分信息失败");
        }
        //6.保存此spu下所有sku
        //6.1 先保存所有sku info pms_sku_info
        List<Skus> skus = spuSaveVo.getSkus();
        skus.forEach(
                it->{
                    String defaultImg ="";
                    List<Images> skuImages = it.getImages();
                    for (Images skuImage : skuImages) {
                        if (Objects.nonNull(skuImage)&&skuImage.getDefaultImg().equals(1)){
                            defaultImg=skuImage.getImgUrl();
                        }
                    }
                    SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                    OrikaBeanMapper.copy(it,skuInfoEntity);
                    skuInfoEntity.setSpuId(spuId);
                    skuInfoEntity.setCatalogId(spuSaveVo.getCatalogId());
                    skuInfoEntity.setBrandId(spuSaveVo.getBrandId());
                    skuInfoEntity.setSkuDefaultImg(defaultImg);
                    skuInfoEntity.setSaleCount(0L);
                    skuInfoService.saveSkuInfo(skuInfoEntity);
                    Long skuId = skuInfoEntity.getSkuId();
                    //6.2 保存sku 图片  pms_sku_images
                    List<SkuImagesEntity> collect = skuImages.stream().map(image -> {
                        SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                        OrikaBeanMapper.copy(image, skuImagesEntity);
                        skuImagesEntity.setSkuId(skuId);
                        return skuImagesEntity;
                    }).collect(Collectors.toList());
                    skuImagesService.saveBatch(collect);
                    //6.3 保存sku 的销售属性 pms_sku_sale_attr_value
                    List<Attr> attrs = it.getAttr();
                    List<SkuSaleAttrValueEntity> SkuSales = attrs.stream().map(
                            attr -> {
                                SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                                OrikaBeanMapper.copy(attr, skuSaleAttrValueEntity);
                                skuSaleAttrValueEntity.setSkuId(skuId);
                                return skuSaleAttrValueEntity;
                            }
                    ).collect(Collectors.toList());
                    skuSaleAttrValueService.saveBatch(SkuSales);
                    //6.4 保存sku满减政策
                    SkuReductionTo skuReductionTo = new SkuReductionTo();
                    OrikaBeanMapper.copy(it,skuReductionTo);
                    skuReductionTo.setSkuId(skuId);
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if (r1.getCode()!=0){
                        log.error("保存商品满减政策失败");
                    }
                }
        );
    }
}
