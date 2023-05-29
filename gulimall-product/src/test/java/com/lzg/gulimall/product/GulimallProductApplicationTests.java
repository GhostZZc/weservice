package com.lzg.gulimall.product;

import com.lzg.gulimall.product.service.ISkuInfoService;
import com.lzg.gulimall.product.vo.skuItemvo.SkuItemVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class GulimallProductApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private ISkuInfoService skuInfoService;

    @Test
    void itemTest() throws ExecutionException, InterruptedException {
        SkuItemVo item = skuInfoService.item(10L);
        System.out.println(item);
    }
}
