package com.lzg.gulimall.search.controller;

import com.lzg.gulimall.common.exception.BizCodeEnum;
import com.lzg.gulimall.common.to.es.SkuEsModel;
import com.lzg.gulimall.common.utils.R;
import com.lzg.gulimall.search.service.IElasticSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


/**
 * @ClassName: ElasticSaveController
 * @Description: elastic商品保存
 * @author: lzg
 * @date: 2023/2/28 10:27
 */
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {

    @Autowired
    private IElasticSaveService elasticSaveService;



    @PostMapping("/product")
    public R upProductToElastic(@RequestBody List<SkuEsModel> skus){
        Boolean hasFail = false;
        try {
            hasFail = elasticSaveService.saveProductToElasticHasFail(skus);
            System.out.println("皋月天清雨渐浓,遥看湖影日灼灼." +
                                "却是芬芬桃色朦,青藤蔓布花正红.");
            System.out.println(1);
        } catch (IOException e) {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if(!hasFail){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }
}
