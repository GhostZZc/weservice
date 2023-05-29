package com.lzg.gulimall.product.controller.web;

import com.lzg.gulimall.common.exception.RRException;
import com.lzg.gulimall.product.service.ISkuInfoService;
import com.lzg.gulimall.product.vo.skuItemvo.SkuItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName: ItemController
 * @Description: 物品详情接口
 * @author: lzg
 * @date: 2023/5/23 11:11
 */
@Controller
@Slf4j
public class ItemController {

    @Autowired
    private ISkuInfoService skuInfoService;


    @RequestMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) {
        log.info("需要查询的商品id为:" + skuId);
        SkuItemVo item = null;
        try {
            item = skuInfoService.item(skuId);
        } catch (ExecutionException e) {
            throw new RRException("查询商品Id为:" + skuId + "失败");
        } catch (InterruptedException e) {
            throw new RRException("查询商品Id为:" + skuId + "失败");
        }
        model.addAttribute("item", item);
        return "item";
    }

}
