package com.lzg.gulimall.product.controller.app;

import com.lzg.gulimall.common.exception.BizCodeEnum;
import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.product.entity.SpuInfoEntity;
import com.lzg.gulimall.product.service.ISpuInfoService;
import com.lzg.gulimall.product.vo.spvsavevo.SpuSaveVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * spu信息
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-13 14:15:26
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private ISpuInfoService pmsSpuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:pmsspuinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = pmsSpuInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("product:pmsspuinfo:info")
    public R info(@PathVariable("id") Long id) {
        SpuInfoEntity pmsSpuInfo = pmsSpuInfoService.getById(id);

        return R.ok().put("pmsSpuInfo", pmsSpuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:pmsspuinfo:save")
    public R save(@RequestBody SpuSaveVo spuSaveVo) {
        pmsSpuInfoService.saveSpuVo(spuSaveVo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:pmsspuinfo:update")
    public R update(@RequestBody SpuInfoEntity pmsSpuInfo) {
        pmsSpuInfoService.updateById(pmsSpuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:pmsspuinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        pmsSpuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    //todo  差2个接口

    /**
     * 上架商品
     * @param spuId
     * @return
     */
    @PostMapping("{spuId}/up")
    public R spuStateUp(@PathVariable Long spuId) {
        Boolean success = pmsSpuInfoService.upSpuBySpuId(spuId);
        return success ? R.ok() : R.error(BizCodeEnum.NO_STOCK_EXCEPTION.getMsg());
    }


}
