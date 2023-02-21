package com.lzg.gulimall.product.controller.app;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.product.entity.AttrGroupEntity;
import com.lzg.gulimall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.lzg.gulimall.product.entity.PmsAttrEntity;
import com.lzg.gulimall.product.service.PmsAttrAttrgroupRelationService;
import com.lzg.gulimall.product.service.PmsAttrGroupService;
import com.lzg.gulimall.product.vo.AttrGroupAttrVo;
import com.lzg.gulimall.product.vo.AttrGroupVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 属性分组
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:27
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;
    @Autowired
    private PmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list/{categoryId}")
    @RequiresPermissions("product:pmsattrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable Long categoryId){
        PageUtils page = pmsAttrGroupService.queryPage(params,categoryId);

        return R.ok().put("page", page);
    }

    @GetMapping("{attrgroupId}/attr/relation")
    public R getAttrByAttrgroupId(@PathVariable Long attrgroupId){
        List<PmsAttrEntity> attrByAttrGroupId = pmsAttrGroupService.getAttrByAttrGroupId(attrgroupId);
        return R.ok().put("data",attrByAttrGroupId);
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R getNoattr(@RequestParam Map<String, Object> params,@PathVariable Long attrgroupId){
        PageUtils noAttrs = pmsAttrGroupService.getNoAttrs(params, attrgroupId);
        return Objects.nonNull(noAttrs)?R.ok().put("page",noAttrs):R.error("多多少少还是出了点问题");
    }


    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupAndAttr(@PathVariable Long catelogId){
        List<AttrGroupAttrVo> byCatelogId = pmsAttrGroupService.getByCatelogId(catelogId);
        return R.ok().put("data",byCatelogId);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    @RequiresPermissions("product:pmsattrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
        AttrGroupVo attrGroupVo = pmsAttrGroupService.getById(attrGroupId);

        return R.ok().put("attrGroup", attrGroupVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:pmsattrgroup:save")
    public R save(@RequestBody AttrGroupEntity pmsAttrGroup){
		pmsAttrGroupService.save(pmsAttrGroup);

        return R.ok();
    }

    /**
     * 建立分组和属性的关系
     * @param
     * @return
     */
    @RequestMapping("/attr/relation")
    @RequiresPermissions("product:pmsattrattrgrouprelation:save")
    public R saveRelation(@RequestBody List<PmsAttrAttrgroupRelationEntity> items){
        pmsAttrAttrgroupRelationService.saveBatch(items);

        return R.ok();
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody List<PmsAttrAttrgroupRelationEntity> items){
        pmsAttrAttrgroupRelationService.removeItems(items);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:pmsattrgroup:update")
    public R update(@RequestBody AttrGroupEntity pmsAttrGroup){
		pmsAttrGroupService.updateById(pmsAttrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:pmsattrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		pmsAttrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
