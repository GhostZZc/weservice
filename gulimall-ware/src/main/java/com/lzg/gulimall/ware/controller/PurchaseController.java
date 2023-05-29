package com.lzg.gulimall.ware.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lzg.gulimall.common.utils.PageUtils;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.ware.vo.PurchaseDoneVo;
import com.lzg.gulimall.ware.vo.PurchaseMergeVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lzg.gulimall.ware.entity.PurchaseEntity;
import com.lzg.gulimall.ware.service.PurchaseService;




/**
 * 采购信息
 *
 * @author lzg
 * @email ${email}
 * @date 2022-12-15 15:01:21
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
        purchase.setUpdateTime(new Date());
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 查询未领取的采购单
     * @param params
     * @return
     */
    @GetMapping("/unreceive/list")
    public R unReceive(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryUnReceiveList(params);
        return R.ok().put("page",page);
    }


    /**
     * 合并采购单项到采购单上
     * @param purchaseMergeVo
     * @return
     */
    @PostMapping("/merge")
    public R mergePurchase(@RequestBody PurchaseMergeVo purchaseMergeVo){
        purchaseService.purchaseMerge(purchaseMergeVo);
        return R.ok();
    }

    /**
     * 领取采购单
     * @param ids
     * @return
     */
    @PostMapping("/received")
    public R receivedPurchase(@RequestBody List<Long> ids){
        purchaseService.receivedPurchaseOrder(ids);
        return R.ok();
    }

    @PostMapping("/done")
    public R donePurchase(@RequestBody PurchaseDoneVo purchaseDoneVo){
        purchaseService.purchaseDone(purchaseDoneVo);
        return R.ok();
    }


}
