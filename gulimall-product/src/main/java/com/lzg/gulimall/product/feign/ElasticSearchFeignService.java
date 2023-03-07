package com.lzg.gulimall.product.feign;

import com.lzg.gulimall.common.to.es.SkuEsModel;
import com.lzg.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("gulimall-search")
public interface ElasticSearchFeignService {



    @PostMapping("/search/save/product")
    R saveProductToElastic(@RequestBody List<SkuEsModel> skuEsModels);
}
