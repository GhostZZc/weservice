package com.lzg.gulimall.product.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.product.entity.ProductAttrValueEntity;
import com.lzg.gulimall.product.service.IAttrService;
import com.lzg.gulimall.product.service.IProductAttrValueService;
import com.lzg.gulimall.product.utils.AttrType;
import com.lzg.gulimall.product.vo.AttrVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private IAttrService pmsAttrService;
    @Autowired
    private IProductAttrValueService productAttrValueService;


    /**
     * 规格参数列表
     */
    @RequestMapping("/base/list/{catelogId}")
    @RequiresPermissions("product:pmsattr:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable Long catelogId){
        PageUtils page = pmsAttrService.queryPage(params,catelogId, AttrType.BASE);

        return R.ok().put("page", page);
    }

    @GetMapping("/sale/list/{catelogId}")
    public  R saleList(@RequestParam Map<String,Object> params,@PathVariable Long catelogId){
        PageUtils page = pmsAttrService.queryPage(params,catelogId, AttrType.SALE);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    @RequiresPermissions("product:pmsattr:info")
    public R info(@PathVariable("attrId") Long attrId){
        AttrVo attrVo = pmsAttrService.getById(attrId);
        return R.ok().put("attr",attrVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:pmsattr:save")
    public R save(@RequestBody AttrVo attrVo){
		pmsAttrService.save(attrVo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:pmsattr:update")
    public R update(@RequestBody AttrVo attrVo){
		pmsAttrService.update(attrVo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:pmsattr:delete")
    public R delete(@RequestBody Long[] attrIds){
		pmsAttrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

    /**
     * 获取spu规格
     * @param spuId
     * @return
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R listForSpu(@PathVariable Long spuId){
        List<ProductAttrValueEntity> list = productAttrValueService.list(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        return R.ok().put("data",productAttrValueService);
    }

    /**
     * 修改spu规格
     * @param spuId
     * @param attrs
     * @return
     */
    @PostMapping("/update/{spuId}")
    public R updateAttr(@PathVariable("spuId") Long spuId,List<ProductAttrValueEntity> attrs){
        productAttrValueService.updateAttr(spuId,attrs);
        return R.ok();
    }



}
