package com.lzg.gulimall.cart.feign;

import com.lzg.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("gulimall-product")
public interface IProductFeignService {

    @RequestMapping("product/skuinfo/info/{skuId}")
    R info(@PathVariable Long skuId);

    @GetMapping("product/productattrvalue/getSaleAttrAsString/{skuId}")
    List<String> getSaleAttrAsString(@PathVariable Long skuId);
}
