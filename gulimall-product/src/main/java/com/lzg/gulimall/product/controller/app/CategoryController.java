package com.lzg.gulimall.product.controller.app;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.product.entity.CategoryEntity;
import com.lzg.gulimall.product.service.PmsCategoryService;
import com.lzg.gulimall.product.vo.CategoryVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 商品三级分类
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private PmsCategoryService pmsCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:pmscategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    @RequiresPermissions("product:pmscategory:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity pmsCategory = pmsCategoryService.getById(catId);

        return R.ok().put("data", pmsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:pmscategory:save")
    public R save(@RequestBody CategoryEntity pmsCategory){
		pmsCategoryService.save(pmsCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:pmscategory:update")
    public R update(@RequestBody CategoryEntity pmsCategory){
		pmsCategoryService.updateById(pmsCategory);

        return R.ok();
    }

    @PostMapping("/update/sort")
    @RequiresPermissions("product:pmscategory:update:sort")
    public R updateBatch(@RequestBody List<CategoryEntity> list){
        pmsCategoryService.updateBatchById(list);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:pmscategory:delete")
    public R delete(@RequestBody List<Long> catIds){
        boolean delete = pmsCategoryService.removeByIds(catIds);

        return delete?R.ok("删除成功"):R.error("删除失败");
    }

    @GetMapping("/tree")
    public R tree(){
        List<CategoryVo> categoryTree = pmsCategoryService.getCategoryTree();
        return R.ok().put("data",categoryTree);
    }

}
