package com.lzg.gulimall.product.feign;

import com.lzg.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-ware")
public interface WareFeignService {


    @PostMapping("/ware/waresku/hasstock")
    R selectSkuHasStock(@RequestBody List<Long> skuIds);
}
