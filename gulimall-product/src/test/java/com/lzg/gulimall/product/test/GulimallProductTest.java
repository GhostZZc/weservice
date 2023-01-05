package com.lzg.gulimall.product.test;

import com.lzg.gulimall.product.entity.BrandEntity;
import com.lzg.gulimall.product.service.PmsBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: GulimallProductTest
 * @Description: 商品测试
 * @author: lzg
 * @date: 2022/12/14 10:18
 */
@SpringBootTest
public class GulimallProductTest {

    @Autowired
    PmsBrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("hello");
        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }
}
